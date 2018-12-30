package dev.tran.nam.gobear.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dev.tran.nam.gobear.R
import dev.tran.nam.gobear.di.module.GlideApp
import nam.tran.data.Logger

object Binding {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(image: ImageView, url: String?) {
        Logger.debug("Image", url)
        url?.run {
            val circularProgressDrawable = CircularProgressDrawable(image.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            GlideApp.with(image).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .transition(DrawableTransitionOptions.withCrossFade()).placeholder(circularProgressDrawable)
                .error(R.drawable.image_place_holder).into(image)
        }
    }
}