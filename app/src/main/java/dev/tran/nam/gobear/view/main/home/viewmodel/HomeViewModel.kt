package dev.tran.nam.gobear.view.main.home.viewmodel

import android.app.Application

import javax.inject.Inject

import tran.nam.core.viewmodel.BaseFragmentViewModel
import tran.nam.core.viewmodel.IProgressViewModel
import nam.tran.domain.entity.state.Resource
import androidx.lifecycle.MutableLiveData

class HomeViewModel @Inject internal constructor(application: Application) : BaseFragmentViewModel(application),
    IProgressViewModel {

    private val results = MutableLiveData<Resource<*>>()

    override fun resource(): Resource<*>? {
        return results.value
    }


}
