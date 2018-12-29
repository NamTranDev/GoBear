package nam.tran.domain.logic.authen

import androidx.lifecycle.LiveData
import javax.inject.Inject

class AuthenUseCase<T> @Inject internal constructor(private val iAuthenRepository: IAuthenRepository<T>) :
    IAuthenUseCase<T> {

    override fun initDataBound(): LiveData<T> {
        return iAuthenRepository.initDataBound()
    }

    override fun login(email: String?, password: String?, isRemember: Boolean) {
        iAuthenRepository.login(email, password, isRemember)
    }


}