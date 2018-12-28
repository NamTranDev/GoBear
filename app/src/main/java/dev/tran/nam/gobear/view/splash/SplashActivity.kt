package dev.tran.nam.gobear.view.splash

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.databinding.ActivitySplashBinding
import tran.nam.core.view.BaseActivity

class SplashActivity : BaseActivity() {

    private var mViewDataBinding: ActivitySplashBinding? = null
    private var handler: Handler? = null
    private var runnable: Runnable? = object : Runnable {
        override fun run() {

        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)

        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId())

        handler = Handler()
        handler!!.postDelayed(runnable, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler!!.removeCallbacks(runnable)
        handler = null
        runnable = null
    }
}
