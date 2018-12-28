package dev.tran.nam.gobear.view.splash.onboard

import androidx.fragment.app.Fragment

import dagger.Binds
import dagger.Module
import tran.nam.core.di.inject.PerFragment

/**
 * Provides on board fragment dependencies.
 */
@Module
abstract class OnBoardFragmentModule {

    @Binds
    @PerFragment
    internal abstract fun fragment(fragment: OnBoardFragment): Fragment
}
