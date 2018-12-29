package nam.tran.flatform.interactor.authen

import androidx.lifecycle.MutableLiveData
import nam.tran.flatform.executor.AppExecutors
import nam.tran.flatform.local.IPreference
import nam.tran.flatform.model.core.StatusCode.EMPTY_FIELD
import nam.tran.flatform.model.core.state.ErrorResource
import nam.tran.flatform.model.core.state.Resource
import nam.tran.flatform.testing.OpenForTesting

@OpenForTesting
class AuthenDataBound constructor(
    private val appExecutors: AppExecutors,
    private val iPreference: IPreference
) {

    val INCORRECT = 1

    val result = MutableLiveData<Resource<Boolean>>()

    fun login(user: String?, password: String?, isRemember: Boolean) {
        appExecutors.diskIO().execute {

            result.postValue(Resource.loading())

            if (emptyUser(user)) {
                return@execute
            }

            if (emptyPassword(password)) {
                return@execute
            }

            Thread.sleep(2000)

            if (!loginFail(user!!, password!!)) {
                iPreference.login(isRemember)
                result.postValue(Resource.success(true))
            }
        }
    }

    fun emptyUser(user: String?): Boolean {
        if (user == null || user == "") {
            result.postValue(Resource.error(msg = ErrorResource("User not empty", EMPTY_FIELD), data = false))
            return true
        }
        return false
    }

    fun emptyPassword(password: String?): Boolean {
        if (password == null || password == "") {
            result.postValue(Resource.error(msg = ErrorResource("Password not empty", EMPTY_FIELD), data = false))
            return true
        }
        return false
    }

    fun loginFail(user: String, password: String): Boolean {
        if (user != "GoBear" && password != "GoBearDemo") {
            result.postValue(
                Resource.error(
                    msg = ErrorResource("Email or Password not correct", INCORRECT),
                    data = false
                )
            )
            return true
        }
        return false
    }

}