package dev.tran.nam.gobear.view.main.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import dev.tran.nam.gobear.view.main.home.viewmodel.IHomeViewModel
import dev.tran.nam.gobear.view.main.home.viewmodel.HomeViewModel

import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.databinding.FragmentHomeBinding
import nam.tran.data.Logger

import tran.nam.core.view.mvvm.BaseFragmentMVVM

class HomeFragment : BaseFragmentMVVM<FragmentHomeBinding, HomeViewModel>(), IHomeViewModel {

    override fun isHaveAnimation(): Boolean {
        return false
    }

    override fun initViewModel(factory: ViewModelProvider.Factory?) {
        mViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onVisible() {
        mViewDataBinding?.viewModel = mViewModel

        mViewModel?.result?.observe(viewLifecycleOwner, Observer {
            Logger.debug(it)
        })

        mViewModel?.state?.observe(viewLifecycleOwner, Observer {
            Logger.debug(it)
        })
    }

    override fun initialized() {
        super.initialized()
        mViewModel?.onRefesh()
    }
}
