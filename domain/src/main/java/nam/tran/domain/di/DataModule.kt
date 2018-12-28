package nam.tran.domain.di

import dagger.Binds
import dagger.Module
import nam.tran.domain.AppUseCase
import nam.tran.domain.interactor.app.IAppUseCase
import nam.tran.flatform.di.DbModule
import nam.tran.flatform.di.NetModule
import javax.inject.Singleton


@Module(includes = [NetModule::class, DbModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    internal abstract fun provideAppUseCase(appUseCase: AppUseCase): IAppUseCase

}
