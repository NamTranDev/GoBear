package dev.tran.nam.gobear.view.main.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Transformations
import dev.tran.nam.gobear.mapper.DataMapper
import nam.tran.data.interactor.rss.IRssUseCae
import tran.nam.core.viewmodel.BaseFragmentViewModel
import javax.inject.Inject

class HomeViewModel @Inject internal constructor(
    application: Application,
    private val iRssUseCae: IRssUseCae,
    dataMapper: DataMapper
) :
    BaseFragmentViewModel(application) {

    val isRefreshing = ObservableBoolean(false)

    private val response = iRssUseCae.initDataBound()

    val result = Transformations.switchMap(response) { it ->
        it.data?.run {
            Transformations.map(this) {
                dataMapper.articleModelMapper.transformModel(it)
            }
        }
    }

    val state = Transformations.switchMap(response) {
        it.networkState
    }

    fun onRefresh() {
        isRefreshing.set(true)
        iRssUseCae.refresh()
    }

    fun logout() {
        iRssUseCae.logout()
        view<IHomeViewModel>()?.let {
            it.onLogout()
        }
    }
}
