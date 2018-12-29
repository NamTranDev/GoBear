package dev.tran.nam.gobear.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.tran.nam.gobear.view.splash.authen.viewmodel.AuthenViewModel
import tran.nam.core.di.ViewModelFactory
import tran.nam.core.di.inject.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthenViewModel::class)
    internal abstract fun bindAuthenViewModel(model: AuthenViewModel): ViewModel
}
