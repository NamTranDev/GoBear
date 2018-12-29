package nam.tran.flatform.interactor.authen

import androidx.lifecycle.MutableLiveData
import nam.tran.flatform.model.core.StatusCode.EMPTY_FIELD
import nam.tran.flatform.model.core.state.ErrorResource
import nam.tran.flatform.model.core.state.Resource
import nam.tran.flatform.executor.AppExecutors
import nam.tran.flatform.local.IPreference

class AuthenDataBound constructor(
    private val appExecutors: AppExecutors,
    private val iPreference: IPreference
) {

    val INCORRECT = 1

    val result = MutableLiveData<Resource<Boolean>>()

    fun login(email: String?, password: String?, isRemember: Boolean) {
        appExecutors.diskIO().execute {

            result.postValue(Resource.loading())

            Thread.sleep(2000)

            if (email == null || email == "") {
                result.postValue(Resource.error(msg = ErrorResource("Email not empty", EMPTY_FIELD), data = false))
                return@execute
            }

            if (password == null || password == "") {
                result.postValue(Resource.error(msg = ErrorResource("Password not empty", EMPTY_FIELD), data = false))
                return@execute
            }

            if (email == "GoBear" && password == "GoBearDemo") {
                iPreference.login(isRemember)
                result.postValue(Resource.success(true))
            } else {
                result.postValue(
                    Resource.error(
                        msg = ErrorResource("Email or Password not correct", INCORRECT),
                        data = false
                    )
                )
            }
        }
    }

}