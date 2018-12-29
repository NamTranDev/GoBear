package nam.tran.data.interactor.rss

import androidx.lifecycle.LiveData
import nam.tran.data.model.Article
import nam.tran.data.model.core.state.Listing
import nam.tran.data.model.core.state.Resource

interface IRssUseCae {
    fun initDataBound(): LiveData<Listing<List<Article>>>
    fun refresh()
}