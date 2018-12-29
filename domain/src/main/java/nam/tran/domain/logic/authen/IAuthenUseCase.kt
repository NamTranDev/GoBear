package nam.tran.domain.logic.authen

import androidx.lifecycle.LiveData

interface IAuthenUseCase<T> {
    fun initDataBound(): LiveData<T>
    fun login(email: String?, password: String?, isRemember: Boolean)
}