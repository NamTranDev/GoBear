package tran.nam.core.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseParentFragment : BaseFragmentInjection(), HasSupportFragmentInjector,
    IFragmentProvider<BaseFragment>, IParentFragmentListener, FragmentHelper.OnChangedFragmentListener {

    lateinit var mChildFragmentHelper: FragmentHelper<BaseFragment>
        @Inject set

    var fragmentInjector: DispatchingAndroidInjector<Fragment>? = null
        @Inject set

    fun addChildFragment(fragment: BaseFragment) {
        mChildFragmentHelper.pushFragment(fragment)
    }

    fun showChildFragment(position: Int) {
        mChildFragmentHelper.showFragment(position)
    }

    fun replaceChildFragment(fragment: BaseFragment) {
        mChildFragmentHelper.replaceFragment(fragment)
    }

    override fun initChildFragment() {
        mChildFragmentHelper.setupFragment(this)
        mChildFragmentHelper.setOnChangedFragmentListener(this)
    }

    override fun fragmentManager(): FragmentManager {
        return childFragmentManager
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentInjector
    }

    override fun popChildFragment(level: Int): Boolean {
        return mChildFragmentHelper.popFragment(level)
    }

    override fun onDestroy() {
        super.onDestroy()
        mChildFragmentHelper.release()
    }

    override fun onChangedFragment(fragment: BaseFragment) {

    }

    override fun onHideFragment(fragment: BaseFragment) {

    }
}
