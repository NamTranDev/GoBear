@file:Suppress("unused")

package tran.nam.core.biding

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import nam.tran.data.model.core.state.Loading
import nam.tran.data.model.core.state.Resource
import nam.tran.data.model.core.state.Status
import tran.nam.core.view.BaseActivityWithFragment
import tran.nam.core.view.BaseParentFragment
import tran.nam.core.viewmodel.IView

object BidingCommon {

    @JvmStatic
    @BindingAdapter("visibleContainLoading")
    fun visibleContainLoading(view: View, resource: Resource<*>?) {
        resource?.let {
            when (it.status) {
                Status.ERROR -> when (it.loading) {
                    Loading.LOADING_DIALOG -> {
                        view.visibility = View.GONE
                        dialogError(view, it.errorResource?.massage, it.errorResource?.code)
                    }
                    Loading.LOADING_NONE -> Toast.makeText(
                        view.context,
                        it.errorResource?.massage,
                        Toast.LENGTH_SHORT
                    ).show()
                    Loading.LOADING_NORMAL -> {
                        if (it.initial) {
                            view.visibility = View.VISIBLE
                        } else {
                            view.visibility = View.GONE
                        }
                    }
                }
                Status.LOADING -> when (it.loading) {
                    Loading.LOADING_DIALOG -> {
                        loadingDialog(view, true)
                    }
                    Loading.LOADING_NONE -> {
                    }
                    Loading.LOADING_NORMAL -> if (it.initial) view.visibility = View.VISIBLE
                }
                Status.SUCCESS -> when (it.loading) {
                    Loading.LOADING_DIALOG -> {
                        loadingDialog(view, false)
                    }
                    Loading.LOADING_NONE -> {
                    }
                    Loading.LOADING_NORMAL -> if (it.initial) view.visibility = View.GONE
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("visibleProgress")
    fun visibleProgress(view: View, resource: Resource<*>?) {
        resource?.let {
            when (it.status) {
                Status.ERROR, Status.SUCCESS -> view.visibility = View.GONE
                Status.LOADING -> when (it.loading) {
                    Loading.LOADING_DIALOG, Loading.LOADING_NONE -> {
                    }
                    Loading.LOADING_NORMAL -> view.visibility = View.VISIBLE
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("visibleView")
    fun visibleView(view: View, resource: Resource<*>?) {
        resource?.let {
            when (it.status) {
                Status.ERROR -> when (it.loading) {
                    Loading.LOADING_DIALOG, Loading.LOADING_NONE -> view.visibility = View.VISIBLE
                    Loading.LOADING_NORMAL -> if (it.initial) {
                        view.visibility = View.GONE
                    } else {
                        view.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> when (it.loading) {
                    Loading.LOADING_DIALOG, Loading.LOADING_NONE -> view.visibility = View.VISIBLE
                    Loading.LOADING_NORMAL -> if (it.initial) view.visibility = View.GONE
                }
                Status.SUCCESS -> view.visibility = View.VISIBLE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("visibleTextError")
    fun visibleTextError(text: TextView, resource: Resource<*>?) {
        resource?.let {
            when (it.status) {
                Status.ERROR -> when (it.loading) {
                    Loading.LOADING_DIALOG -> {
                    }
                    Loading.LOADING_NONE -> {
                    }
                    Loading.LOADING_NORMAL -> {
                        text.visibility = View.VISIBLE
                        text.text = it.errorResource?.massage
                    }
                }
                Status.LOADING, Status.SUCCESS -> text.visibility = View.GONE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("visibleButtonError")
    fun visibleButtonError(bt: Button, resource: Resource<*>?) {
        resource?.let {
            when (it.status) {
                Status.ERROR -> {
                    bt.visibility = View.VISIBLE
                    bt.setOnClickListener {
                        resource.retry?.invoke()
                    }
                }
                Status.LOADING, Status.SUCCESS -> bt.visibility = View.GONE
            }
        }
    }

    private fun loadingDialog(view: View, isShow: Boolean?) {
        val context = view.context
        if (context is IView) {
            if (isShow!!) {
                (context as IView).showDialogLoading()
            } else {
                (context as IView).hideDialogLoading()
            }
        } else {
            if (context is BaseActivityWithFragment) {
                val fragmentHelper = context.mFragmentHelper
                val fragment = fragmentHelper?.getCurrentFragment()
                if (fragment != null && fragment is BaseParentFragment) {
                    val fragmentHelperChild = fragment.mChildFragmentHelper
                    val childFragment = fragmentHelperChild.getCurrentFragment()
                    if (childFragment is IView) {
                        if (isShow!!) {
                            (childFragment as IView).showDialogLoading()
                        } else {
                            (childFragment as IView).hideDialogLoading()
                        }
                    }
                }
            }
        }
    }

    private fun dialogError(view: View, error: String?, codeError: Int?) {
        val context = view.context
        if (context is IView) {
            (context as IView).onShowDialogError(error, codeError)
        } else {
            if (context is BaseActivityWithFragment) {
                val fragmentHelper = context.mFragmentHelper
                val fragment = fragmentHelper?.getCurrentFragment()
                if (fragment != null && fragment is BaseParentFragment) {
                    val fragmentHelperChild = fragment.mChildFragmentHelper
                    val childFragment = fragmentHelperChild.getCurrentFragment()
                    if (childFragment is IView) {
                        (childFragment as IView).onShowDialogError(error, codeError)
                    }
                }
            }
        }
    }
}
