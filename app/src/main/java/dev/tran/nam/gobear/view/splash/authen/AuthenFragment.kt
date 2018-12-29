package dev.tran.nam.gobear.view.splash.authen

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.databinding.FragmentAuthenBinding
import dev.tran.nam.gobear.view.main.MainActivity
import dev.tran.nam.gobear.view.splash.authen.viewmodel.AuthenViewModel
import dev.tran.nam.gobear.view.splash.authen.viewmodel.IAuthenViewModel
import tran.nam.core.view.mvvm.BaseFragmentMVVM
import tran.nam.util.start

class AuthenFragment : BaseFragmentMVVM<FragmentAuthenBinding, AuthenViewModel>(), IAuthenViewModel {

    override fun initViewModel(factory: ViewModelProvider.Factory?) {
        mViewModel = ViewModelProviders.of(this, factory).get(AuthenViewModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_authen
    }

    override fun onVisible() {
        mViewDataBinding?.viewModel = mViewModel

        mViewModel?.result?.observe(viewLifecycleOwner, Observer {
            it?.run {
                if (this.isLoading()) {
                    showDialogLoading()
                } else {
                    if (this.data!!) {
                        hideDialogLoading()
                        activity?.start<MainActivity>(true)
                    } else {
                        onShowDialogError(this.errorResource?.massage, this.errorResource?.code)
                    }
                }
            }
        })
    }
}
