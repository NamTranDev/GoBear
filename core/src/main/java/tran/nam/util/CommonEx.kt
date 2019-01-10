@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package tran.nam.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import tran.nam.core.R
import tran.nam.core.view.BaseActivityWithFragment
import tran.nam.core.view.BaseParentFragment

inline fun <reified T> Activity.start(clearBackStack: Boolean = false, bundle: Bundle? = null,animation : Array<Int>? = null) {
    val intent = Intent(this, T::class.java)
    if (clearBackStack)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    bundle?.let {
        intent.putExtras(bundle)
    }
    startActivity(intent)
    if (animation == null)
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out)
    else
        overridePendingTransition(animation[0], animation[1])
}

inline fun <reified T> Activity.startForResult(requestCode: Int, bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(bundle)
    }
    startActivityForResult(intent, requestCode)
}

inline fun <reified T : Fragment> createNewFragment(context: Context, bundle: Bundle? = null): T {
    return T::class.java.cast(Fragment.instantiate(context, T::class.java.name, bundle))
}

inline fun <reified T : Fragment> Context.newFragment(bundle: Bundle? = null): T {
    return T::class.java.cast(Fragment.instantiate(this, T::class.java.name, bundle))
}

inline fun <reified T : BaseActivityWithFragment> Fragment.getActivityFragment(): T? {
    activity ?: return null
    return T::class.java.cast(activity)
}

inline fun SharedPreferences.put(body: SharedPreferences.Editor.() -> Unit) {
    val editor = this.edit()
    editor.body()
    editor.apply()
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()