package dev.tran.nam.gobear.view.main.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.databinding.FragmentHomeBinding
import dev.tran.nam.gobear.view.main.detail.DetailFragment
import dev.tran.nam.gobear.view.main.home.viewmodel.HomeViewModel
import dev.tran.nam.gobear.view.main.home.viewmodel.IHomeViewModel
import dev.tran.nam.gobear.view.splash.SplashActivity
import nam.tran.data.executor.AppExecutors
import tran.nam.core.biding.FragmentDataBindingComponent
import tran.nam.core.view.mvvm.BaseFragmentMVVM
import tran.nam.util.Constant
import tran.nam.util.createNewFragment
import tran.nam.util.start
import javax.inject.Inject

class HomeFragment : BaseFragmentMVVM<FragmentHomeBinding, HomeViewModel>(), IHomeViewModel {

    @Inject
    lateinit var appExecutors: AppExecutors

    private val dataBindingComponent = FragmentDataBindingComponent(this)

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

        val adapter = ArticleAdapter(dataBindingComponent, appExecutors) {
            val bundle = Bundle()
            bundle.putSerializable(Constant.ARGUMENT_KEY_ARTICLE,it)
            addFragmentFromActivity(createNewFragment<DetailFragment>(context!!,bundle))
        }

        binding.rvArticle.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.rvArticle.adapter = adapter

        mViewModel?.result?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        mViewModel?.state?.observe(viewLifecycleOwner, Observer {
            if (it.isLoading()) {
                mViewModel?.isRefreshing?.set(true)
            } else {
                mViewModel?.isRefreshing?.set(false)
                if (!it.isSuccess()) {
                    onShowDialogError(it.errorResource?.message, it.errorResource?.code)
                }
            }
        })
    }

    override fun initialized() {
        super.initialized()
        mViewModel?.onRefresh()
    }

    override fun onLogout() {
        val bundle = Bundle()
        bundle.putBoolean(Constant.INTENT_KEY_LOGIN,true)
        activity?.start<SplashActivity>(true,bundle)
    }
}
