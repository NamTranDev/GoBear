package nam.tran.flatform.di

import dagger.Binds
import dagger.Module
import nam.tran.domain.logic.authen.AuthenUseCase
import nam.tran.domain.logic.authen.IAuthenRepository
import nam.tran.domain.logic.authen.IAuthenUseCase
import nam.tran.flatform.api.NetModule
import nam.tran.flatform.database.DbModule
import nam.tran.flatform.interactor.app.AppRepository
import nam.tran.flatform.interactor.app.IAppRepository
import nam.tran.flatform.interactor.authen.AuthenRepository
import nam.tran.flatform.model.core.state.Listing
import nam.tran.flatform.model.core.state.Resource
import javax.inject.Singleton


@Module(includes = [NetModule::class, DbModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    internal abstract fun provideAppRepository(appRepository: AppRepository): IAppRepository

    @Binds
    @Singleton
    internal abstract fun provideAuthenUserCase(authenUseCase: AuthenUseCase<Listing<Resource<Boolean>>>): IAuthenUseCase<Listing<Resource<Boolean>>>

    @Binds
    @Singleton
    internal abstract fun provideAuthenRepository(authenRepository: AuthenRepository): IAuthenRepository<Listing<Resource<Boolean>>>
}
