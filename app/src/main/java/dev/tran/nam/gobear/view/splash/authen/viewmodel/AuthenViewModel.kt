package dev.tran.nam.gobear.view.splash.authen.viewmodel

import android.app.Application
import androidx.lifecycle.Transformations
import nam.tran.domain.logic.authen.IAuthenUseCase
import nam.tran.flatform.model.core.state.Listing
import nam.tran.flatform.model.core.state.Resource
import tran.nam.core.viewmodel.BaseFragmentViewModel
import javax.inject.Inject

class AuthenViewModel @Inject internal constructor(
    application: Application,
    private val iAuthenUseCase: IAuthenUseCase<Listing<Resource<Boolean>>>
) : BaseFragmentViewModel(application) {

    val response = iAuthenUseCase.initDataBound()

    val result = Transformations.switchMap(response) {
        it.data
    }

    fun onLogin(email: String?, password: String?, checked: Boolean?) {
        iAuthenUseCase.login(email, password, checked ?: false)
    }
}
