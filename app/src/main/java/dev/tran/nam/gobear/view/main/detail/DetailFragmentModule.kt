package dev.tran.nam.gobear.view.main.detail

import androidx.fragment.app.Fragment

import dagger.Binds
import dagger.Module
import tran.nam.core.di.inject.PerFragment

/**
 * Provides detail fragment dependencies.
 */
@Module
abstract class DetailFragmentModule {

    @Binds
    @PerFragment
    internal abstract fun fragment(fragment: DetailFragment): Fragment
}
