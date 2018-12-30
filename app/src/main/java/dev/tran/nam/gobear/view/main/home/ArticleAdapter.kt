package dev.tran.nam.gobear.view.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.databinding.AdapterArticleBinding
import dev.tran.nam.gobear.model.ArticleModel
import nam.tran.data.executor.AppExecutors
import tran.nam.common.DataBoundListAdapter

class ArticleAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    val goToDetail: (ArticleModel?) -> Unit
) : DataBoundListAdapter<ArticleModel, AdapterArticleBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<ArticleModel>() {
        override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return oldItem.title == oldItem.title
        }

        override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return oldItem.link == newItem.link
        }

    }) {
    override fun createBinding(parent: ViewGroup): AdapterArticleBinding {
        val binding: AdapterArticleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_article,
            parent,
            false,
            dataBindingComponent
        )
        binding.root.setOnClickListener {
            goToDetail.invoke(binding.article)
        }
        return binding
    }

    override fun bind(binding: AdapterArticleBinding, item: ArticleModel) {
        binding.article = item
    }
}