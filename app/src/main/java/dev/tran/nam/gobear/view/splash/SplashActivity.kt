package dev.tran.nam.gobear.view.splash

import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.view.splash.authen.AuthenFragment
import dev.tran.nam.gobear.view.splash.onboard.OnBoardFragment
import tran.nam.core.view.BaseActivityWithFragment
import tran.nam.core.view.BaseFragment
import tran.nam.util.Constant
import tran.nam.util.createNewFragment

class SplashActivity : BaseActivityWithFragment() {

    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override val fragments: Array<BaseFragment>
        get() = arrayOf(
            if (intent.extras != null && intent?.extras!!.getBoolean(Constant.INTENT_KEY_LOGIN,false)) createNewFragment<AuthenFragment>(
                this
            ) else createNewFragment<OnBoardFragment>(this)
        )

    override val contentLayoutId: Int
        get() = R.id.splash_container
}
