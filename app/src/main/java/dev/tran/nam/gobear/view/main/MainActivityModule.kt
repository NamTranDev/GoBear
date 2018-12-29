package dev.tran.nam.gobear.view.main

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.tran.nam.gobear.view.main.home.HomeFragment
import dev.tran.nam.gobear.view.main.home.HomeFragmentModule
import tran.nam.core.di.inject.PerActivity
import tran.nam.core.di.inject.PerFragment
import tran.nam.core.view.BaseFragment
import tran.nam.core.view.IFragmentProvider

/**
 * Provides main activity dependencies.
 */
@Module
abstract class MainActivityModule {

    /**
     * Provides the injector for the [HomeFragmentModule], which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    internal abstract fun injectorHomeFragment(): HomeFragment

    @Binds
    @PerActivity
    internal abstract fun activity(activity: MainActivity): AppCompatActivity

    @Binds
    @PerActivity
    internal abstract fun providerFragmentHelper(activity: MainActivity): IFragmentProvider<BaseFragment>
}
