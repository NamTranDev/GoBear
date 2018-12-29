package dev.tran.nam.gobear.view.main.home.viewmodel

import android.app.Application
import androidx.lifecycle.Transformations
import dev.tran.nam.gobear.mapper.DataMapper
import nam.tran.data.interactor.rss.IRssUseCae
import tran.nam.core.viewmodel.BaseFragmentViewModel
import javax.inject.Inject

class HomeViewModel @Inject internal constructor(application: Application, private val iRssUseCae: IRssUseCae,dataMapper: DataMapper) :
    BaseFragmentViewModel(application) {

    private val response = iRssUseCae.initDataBound()

    val result = Transformations.switchMap(response) { it ->
        Transformations.map(it.data) {
            dataMapper.articleModelMapper.transformModel(it)
        }
    }

    val state = Transformations.switchMap(response){
        it.networkState
    }

    fun onRefesh() {
        iRssUseCae.refresh()
    }


}
