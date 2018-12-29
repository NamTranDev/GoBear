package dev.tran.nam.gobear.view.splash.authen

import androidx.fragment.app.Fragment

import dagger.Binds
import dagger.Module
import tran.nam.core.di.inject.PerFragment

/**
 * Provides authen fragment dependencies.
 */
@Module
abstract class AuthenFragmentModule {

    @Binds
    @PerFragment
    internal abstract fun fragmentInject(fragment: AuthenFragment): Fragment
}
