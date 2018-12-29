package dev.tran.nam.gobear.view.splash.onboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.databinding.FragmentOnBoardBinding
import dev.tran.nam.gobear.view.splash.authen.AuthenFragment
import tran.nam.core.view.BaseFragmentInjection
import tran.nam.util.createNewFragment

class OnBoardFragment : BaseFragmentInjection() {

    private lateinit var mViewDataBinding: FragmentOnBoardBinding

    private lateinit var mAdapter: OnBoardingAdapter

    override fun isHaveAnimation(): Boolean {
        return false
    }

    public override fun layoutId(): Int {
        return R.layout.fragment_on_board
    }

    override fun initLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        return mViewDataBinding.root
    }

    override fun onVisible() {
        super.onVisible()

        mViewDataBinding.view = this

        mAdapter = OnBoardingAdapter()
        mViewDataBinding.viewpager.adapter = mAdapter
        mViewDataBinding.tabDot.setupWithViewPager(mViewDataBinding.viewpager, true);
    }

    fun goToAuthen(){
        replaceFragmentFromActivity(createNewFragment<AuthenFragment>(context!!))
    }
}
