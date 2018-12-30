package nam.tran.data.interactor.authen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nam.tran.data.executor.AppExecutors
import nam.tran.data.local.IPreference
import nam.tran.data.model.core.state.Listing
import nam.tran.data.model.core.state.Resource
import javax.inject.Inject

class AuthenUseCase @Inject internal constructor(
    private val appExecutors: AppExecutors,
    private val iPreference: IPreference
) : IAuthenUseCase {

    private lateinit var authenDataBound: AuthenDataBound

    override fun initDataBound(): LiveData<Listing<Resource<Boolean>>> {
        authenDataBound = AuthenDataBound(appExecutors, iPreference)
        val result = MutableLiveData<Listing<Resource<Boolean>>>()
        result.postValue(Listing(authenDataBound.result))
        return result
    }

    override fun login(email: String?, password: String?, isRemember: Boolean) {
        authenDataBound.login(email, password, isRemember)
    }

}