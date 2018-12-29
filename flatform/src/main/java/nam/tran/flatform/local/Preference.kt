package nam.tran.flatform.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class Preference @Inject
internal constructor(mApp: Context) : IPreference {

    private val mPref: SharedPreferences

    init {
        mPref = mApp.getSharedPreferences(SHARED_REFERENCE_NAME, Context.MODE_PRIVATE)
    }

    companion object {

        /**
         * normal configurations
         */
        private val SHARED_REFERENCE_NAME = "Application Config"
        private val AUTHENTICATION = "Authentication"
    }

    override fun login(isRemember: Boolean) {
        mPref.edit().putBoolean(AUTHENTICATION,isRemember).apply()
    }

    override fun isLogin(): Boolean {
        return mPref.getBoolean(AUTHENTICATION,false)
    }
}
