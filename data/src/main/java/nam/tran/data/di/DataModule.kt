package nam.tran.data.di

import dagger.Binds
import dagger.Module
import nam.tran.data.api.NetModule
import nam.tran.data.database.DbModule
import nam.tran.data.interactor.authen.AuthenUseCase
import nam.tran.data.interactor.authen.IAuthenUseCase
import nam.tran.data.interactor.rss.IRssUseCae
import nam.tran.data.interactor.rss.RssUseCase
import nam.tran.data.local.PreferenceModule
import javax.inject.Singleton


@Module(includes = [NetModule::class, DbModule::class, PreferenceModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    internal abstract fun provideAuthenUserCase(authenUseCase: AuthenUseCase): IAuthenUseCase

    @Binds
    @Singleton
    internal abstract fun provideRssUserCase(rssUseCase: RssUseCase): IRssUseCae
}
