package nam.tran.flatform.local

import javax.inject.Singleton

import dagger.Binds
import dagger.Module
import nam.tran.flatform.local.IPreference
import nam.tran.flatform.local.Preference

@Module
abstract class PreferenceModule {

    @Binds
    @Singleton
    abstract fun providePreference(preferenceProvider: Preference): IPreference
}
