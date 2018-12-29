package nam.tran.flatform.interactor.authen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nam.tran.domain.logic.authen.IAuthenRepository
import nam.tran.flatform.executor.AppExecutors
import nam.tran.flatform.local.IPreference
import nam.tran.flatform.model.core.state.Listing
import nam.tran.flatform.model.core.state.Resource
import javax.inject.Inject

class AuthenRepository @Inject internal constructor(
    private val appExecutors: AppExecutors,
    private val iPreference: IPreference
) : IAuthenRepository<Listing<Resource<Boolean>>> {

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