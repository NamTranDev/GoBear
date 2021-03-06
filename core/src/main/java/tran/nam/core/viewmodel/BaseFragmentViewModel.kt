package tran.nam.core.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import nam.tran.data.Logger
import java.lang.ref.WeakReference

open class BaseFragmentViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    @Volatile
    var mViewWeakReference: WeakReference<IView>? = null

    protected inline fun<reified V: IView> view(): V? {
        if (mViewWeakReference == null || mViewWeakReference?.get() == null)
            return null
        return V::class.java.cast(mViewWeakReference?.get())
    }

    fun onAttach(view: IView) {
        Logger.w("BaseFragmentViewModel : onAttach()")
        mViewWeakReference = WeakReference(view)
        view.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreated() {
        Logger.w("BaseFragmentViewModel : onCreated()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
        Logger.w("BaseFragmentViewModel : onStart()")
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        Logger.w("BaseFragmentViewModel : onResume()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        Logger.w("BaseFragmentViewModel : onPause()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
        Logger.w("BaseFragmentViewModel : onStop()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun onDestroy() {
        Logger.w("BaseFragmentViewModel : onDestroy()")
        val viewWeakReference = this.mViewWeakReference
        if (viewWeakReference != null) {
            val view = viewWeakReference.get()
            view?.lifecycle?.removeObserver(this)
        }
    }
}
