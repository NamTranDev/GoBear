package dev.tran.nam.gobear.view.splash.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.databinding.FragmentOnBoardBinding
import dev.tran.nam.gobear.view.main.MainActivity
import dev.tran.nam.gobear.view.splash.authen.AuthenFragment
import nam.tran.data.local.IPreference
import tran.nam.core.view.BaseFragmentInjection
import tran.nam.util.Constant
import tran.nam.util.createNewFragment
import tran.nam.util.start
import javax.inject.Inject

class OnBoardFragment : BaseFragmentInjection() {

    @Inject
    lateinit var iPreference: IPreference

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

    fun goToAuthen() {
        if (iPreference.isRememberLogin())
            activity?.start<MainActivity>(true)
        else{
            val bundle = Bundle()
            bundle.putBoolean(Constant.ARGUMENT_KEY_LOGIN,true)
            replaceFragmentFromActivity(createNewFragment<AuthenFragment>(context!!,bundle))
        }

    }
}
