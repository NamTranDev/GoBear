package dev.tran.nam.gobear.view.splash.authen.viewmodel

import android.app.Application
import androidx.lifecycle.Transformations
import nam.tran.data.interactor.authen.IAuthenUseCase
import tran.nam.core.viewmodel.BaseFragmentViewModel
import javax.inject.Inject

class AuthenViewModel @Inject internal constructor(
    application: Application,
    private val iAuthenUseCase: IAuthenUseCase
) : BaseFragmentViewModel(application) {

    val response = iAuthenUseCase.initDataBound()

    val result = Transformations.switchMap(response) {
        it.data
    }

    fun onLogin(email: String?, password: String?, checked: Boolean?) {
        iAuthenUseCase.login(email, password, checked ?: false)
    }
}
