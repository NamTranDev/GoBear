package nam.tran.data.interactor.rss

import android.annotation.SuppressLint
import android.util.Xml
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nam.tran.data.BuildConfig
import nam.tran.data.Logger
import nam.tran.data.database.DbProvider
import nam.tran.data.executor.AppExecutors
import nam.tran.data.model.Article
import nam.tran.data.model.ArticleResponse
import nam.tran.data.model.core.state.ErrorResource
import nam.tran.data.model.core.state.Resource
import nam.tran.data.testing.OpenForTesting
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.InputStream
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


@OpenForTesting
class RssDataBound constructor(private val appExecutors: AppExecutors, private val dbProvider: DbProvider) {

    val result = loadFromDb()
    val state = MutableLiveData<Resource<*>>()

    @Suppress("RedundantLambdaArrow")
    fun onRefresh() {
        state.postValue(Resource.loading<Any>())

        appExecutors.diskIO().execute {
            val response = getRssNew()
            if (response.isSuccess) {
                saveCallResult(response.data!!)
                state.postValue(Resource.success<Any>())
            } else {
                state.postValue(Resource.error<Any>(ErrorResource(response.msgError, response.errorCode)))
            }
        }
    }

    @WorkerThread
    private fun saveCallResult(data: List<Article>) {
        dbProvider.articleAccess().insert(data)
    }

    fun getRssNew(): ArticleResponse {
        var link = BuildConfig.LINK_RSS

        if (!link.startsWith("http://") && !link.startsWith("https://"))
            link = "http://" + link

        val url = URL(link)
        val inputStream = url.openConnection().getInputStream()
        return parseXML(inputStream)
    }

    @SuppressLint("SimpleDateFormat")
    fun parseXML(inputStream: InputStream?): ArticleResponse {

        var isItem = false

        var title: String? = null
        var link: String? = null
        var description: String? = null
        var pubDate: String? = null
        var guid: String? = null

        val listData = mutableListOf<Article>()

        try {
            val xmlPullParser = Xml.newPullParser()
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            xmlPullParser.setInput(inputStream, null)
            xmlPullParser.nextTag()
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                val eventType = xmlPullParser.eventType
                val name = xmlPullParser.name ?: continue
                if (eventType == XmlPullParser.END_TAG) {
                    if (name == "item") {
                        isItem = false
                    }
                    continue
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if (name == "item") {
                        isItem = true
                        continue
                    }
                }

                Logger.debug("Parsing name ==> $name")

                var content = ""
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    content = xmlPullParser.text
                    xmlPullParser.nextTag()
                }

                if (name == "title") {
                    title = content
                } else if (name == "link") {
                    link = content
                } else if (name == "description") {
                    description = content
                } else if (name == "pubDate") {
                    pubDate = content
                } else if (name == "guid") {
                    guid = content
                }

                if (title != null && link != null && description != null && pubDate != null && guid != null) {
                    if (isItem) {
                        val format = "E, d MMM yyyy HH:mm:ss Z"
                        listData.add(Article(title, description, SimpleDateFormat(format, Locale.ENGLISH).parse(pubDate), link, guid))
                    }

                    title = null
                    link = null
                    description = null
                    pubDate = null
                    guid = null
                    isItem = false
                }
            }
            return ArticleResponse(listData, true)
        } catch (e: XmlPullParserException) {
            Logger.debug(e)
            return ArticleResponse(isSuccess = false, msgError = e.message)
        } catch (e: Exception) {
            Logger.debug(e)
            return ArticleResponse(isSuccess = false, msgError = e.message)
        } finally {
            inputStream?.close()
        }
    }

    fun loadFromDb(): LiveData<List<Article>> {
        return dbProvider.articleAccess().getArticle()
    }

}