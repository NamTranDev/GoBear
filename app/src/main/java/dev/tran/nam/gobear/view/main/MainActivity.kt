package dev.tran.nam.gobear.view.main

import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.view.main.home.HomeFragment
import tran.nam.core.view.BaseActivityWithFragment
import tran.nam.core.view.BaseFragment
import tran.nam.util.createNewFragment


class MainActivity : BaseActivityWithFragment() {

    override val fragments: Array<BaseFragment>
        get() = arrayOf(createNewFragment<HomeFragment>(this))

    override val contentLayoutId: Int
        get() = R.id.main_contain

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

}
