package nam.tran.data.interactor.authen

import androidx.lifecycle.LiveData
import nam.tran.data.model.core.state.Listing
import nam.tran.data.model.core.state.Resource

interface IAuthenUseCase {
    fun initDataBound(): LiveData<Listing<Resource<Boolean>>>
    fun login(email: String?, password: String?, isRemember: Boolean)
}