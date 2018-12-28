package dev.tran.nam.gobear.view.splash

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.tran.nam.gobear.view.splash.onboard.OnBoardFragment
import dev.tran.nam.gobear.view.splash.onboard.OnBoardFragmentModule
import tran.nam.core.di.inject.PerActivity
import tran.nam.core.di.inject.PerFragment
import tran.nam.core.view.BaseFragment
import tran.nam.core.view.IFragmentProvider

/**
 * Provides main activity dependencies.
 */
@Module
abstract class SplashActivityModule {

    /**
     * Provides the injector for the [OnBoardFragmentModule], which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = [OnBoardFragmentModule::class])
    internal abstract fun injectorOnBoardFragment(): OnBoardFragment

    @Binds
    @PerActivity
    internal abstract fun activity(activity: SplashActivity): AppCompatActivity

    @Binds
    @PerActivity
    internal abstract fun providerFragmentHelper(activity: SplashActivity): IFragmentProvider<BaseFragment>
}