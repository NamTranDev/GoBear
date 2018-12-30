package dev.tran.nam.gobear.view.main.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.databinding.FragmentDetailBinding
import dev.tran.nam.gobear.model.ArticleModel
import tran.nam.core.view.BaseFragmentInjection
import tran.nam.util.Constant

class DetailFragment : BaseFragmentInjection() {

    private lateinit var mViewDataBinding: FragmentDetailBinding

    public override fun layoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun initLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        return mViewDataBinding.root
    }

    override fun onVisible() {
        val article = arguments?.getSerializable(Constant.ARGUMENT_KEY_ARTICLE)
        article.run {
            mViewDataBinding.article = this as ArticleModel
        }
    }

    fun onBack(){
        activity?.onBackPressed()
    }
}
