package nam.tran.flatform.interactor.app

import nam.tran.flatform.local.IPreference
import javax.inject.Inject

class AppRepository @Inject internal constructor(
    private val iPreference: IPreference
) : IAppRepository {


}