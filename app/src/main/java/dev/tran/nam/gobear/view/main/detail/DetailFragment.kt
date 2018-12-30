package dev.tran.nam.gobear.view.main.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.databinding.FragmentDetailBinding
import dev.tran.nam.gobear.model.ArticleModel
import tran.nam.core.view.BaseFragmentInjection
import tran.nam.util.Constant

class DetailFragment : BaseFragmentInjection() {

    private lateinit var mViewDataBinding: FragmentDetailBinding

    private var article : ArticleModel? = null

    public override fun layoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun initLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        return mViewDataBinding.root
    }

    override fun onVisible() {
        (activity as AppCompatActivity).setSupportActionBar(mViewDataBinding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        mViewDataBinding.toolbar.setNavigationOnClickListener{
            onBack()
        }

        article = arguments?.getSerializable(Constant.ARGUMENT_KEY_ARTICLE) as ArticleModel
        article.run {
            mViewDataBinding.article = this as ArticleModel
        }
    }

    override fun initialized() {
        super.initialized()
        mViewDataBinding.webview.loadUrl(article?.link)
    }

    private fun onBack(){
        activity?.onBackPressed()
    }
}
