package dev.tran.nam.gobear.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import tran.nam.core.di.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
