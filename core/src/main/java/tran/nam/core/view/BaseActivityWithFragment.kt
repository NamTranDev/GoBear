package tran.nam.core.view


import androidx.fragment.app.FragmentManager
import javax.inject.Inject

abstract class BaseActivityWithFragment : BaseActivityInjection(), IFragmentProvider<BaseFragment>, FragmentHelper.OnChangedFragmentListener {

    lateinit var mFragmentHelper: FragmentHelper<BaseFragment>
        @Inject set

    fun addFragment(fragment: BaseFragment) {
        mFragmentHelper.pushFragment(fragment)
    }

    fun showFragment(position: Int) {
        mFragmentHelper.showFragment(position)
    }

    fun replaceFragment(fragment: BaseFragment) {
        mFragmentHelper.replaceFragment(fragment)
    }

    override fun initFragment() {
        mFragmentHelper.setupFragment(this)
        mFragmentHelper.setOnChangedFragmentListener(this)
    }

    override fun fragmentManager(): FragmentManager {
        return supportFragmentManager
    }

    fun popToRoot() {
        mFragmentHelper.popFragmentToRoot()
    }

    override fun onDestroy() {
        super.onDestroy()
        mFragmentHelper.release()
    }

    override fun onBackPressed() {
        if (!mFragmentHelper.popFragment()){
            super.onBackPressed()
        }
    }

    override fun onChangedFragment(fragment: BaseFragment) {

    }

    override fun onHideFragment(fragment: BaseFragment) {

    }
}
