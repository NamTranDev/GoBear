package tran.nam.core.view

import androidx.fragment.app.FragmentManager

interface IFragmentProvider<T : BaseFragment> {

    val fragments: Array<T>

    val contentLayoutId: Int

    fun fragmentManager(): FragmentManager

}
