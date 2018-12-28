package dev.tran.nam.gobear.view


import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dev.tran.nam.gobear.di.component.AppComponent
import dev.tran.nam.gobear.di.component.DaggerAppComponent
import javax.inject.Inject

class AppState : MultiDexApplication(), Application.ActivityLifecycleCallbacks,
    HasActivityInjector {

    var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null
        @Inject set

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent!!.inject(this)
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle?) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }
}