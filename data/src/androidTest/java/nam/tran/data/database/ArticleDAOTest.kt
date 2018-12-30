package nam.tran.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import nam.tran.data.LiveDataTestUtil.getValue
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.`is`

@RunWith(AndroidJUnit4::class)
class ArticleDAOTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertArticle(){
        val loadedFirst = getValue(db.articleAccess().getArticle())
        MatcherAssert.assertThat(loadedFirst, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loadedFirst.size,`is`(0))
        val articles = ArticleDataMock.ceateArticle()
        db.articleAccess().insert(articles)
        val loaded = getValue(db.articleAccess().getArticle())
        MatcherAssert.assertThat(loaded, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.size,`is`(1))
    }

}