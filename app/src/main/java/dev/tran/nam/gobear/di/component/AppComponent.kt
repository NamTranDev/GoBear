package dev.tran.nam.gobear.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dev.tran.nam.gobear.di.module.AppModule
import dev.tran.nam.gobear.view.AppState
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<AppState> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(appState: AppState): Builder

        fun build(): AppComponent
    }
}
