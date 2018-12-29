package dev.tran.nam.gobear.view.main.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import nam.tran.flatform.model.core.state.Resource
import tran.nam.core.viewmodel.BaseFragmentViewModel
import tran.nam.core.viewmodel.IProgressViewModel
import javax.inject.Inject

class HomeViewModel @Inject internal constructor(application: Application) : BaseFragmentViewModel(application),
    IProgressViewModel {

    private val results = MutableLiveData<Resource<*>>()

    override fun resource(): Resource<*>? {
        return results.value
    }


}
