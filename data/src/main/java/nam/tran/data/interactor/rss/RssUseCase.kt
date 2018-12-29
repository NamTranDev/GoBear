package nam.tran.data.interactor.rss

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nam.tran.data.database.DbProvider
import nam.tran.data.executor.AppExecutors
import nam.tran.data.interactor.authen.AuthenDataBound
import nam.tran.data.model.Article
import nam.tran.data.model.core.state.Listing
import nam.tran.data.model.core.state.Resource
import javax.inject.Inject

class RssUseCase @Inject internal constructor(
    private val appExecutors: AppExecutors,
    private val dbProvider: DbProvider
) : IRssUseCae{

    private lateinit var rssDataBound: RssDataBound

    override fun initDataBound(): LiveData<Listing<List<Article>>> {
        rssDataBound = RssDataBound(appExecutors, dbProvider)
        val result = MutableLiveData<Listing<List<Article>>>()
        result.postValue(Listing(rssDataBound.result,rssDataBound.state))
        return result
    }

    override fun refresh() {
        rssDataBound.onRefresh()
    }

}