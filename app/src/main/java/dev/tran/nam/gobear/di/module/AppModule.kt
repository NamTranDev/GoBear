package dev.tran.nam.gobear.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import dev.tran.nam.gobear.view.AppState
import nam.tran.domain.di.DataModule
import javax.inject.Singleton

/**
 * Provides application-wide dependencies.
 */
@Module(includes = [AndroidSupportInjectionModule::class, ViewModelModule::class, DataModule::class])
abstract class AppModule {

    @Binds
    @Singleton
    internal abstract/*
     * Singleton annotation isn't necessary since Application instance is unique but is here for
     * convention. In general, providing Activity, Fragment, BroadcastReceiver, etc does not require
     * them to be scoped since they are the components being injected and their instance is unique.
     *
     * However, having a scope annotation makes the module easier to read. We wouldn't have to look
     * at what is being provided in order to understand its scope.
     */ fun application(app: AppState): Application
}
