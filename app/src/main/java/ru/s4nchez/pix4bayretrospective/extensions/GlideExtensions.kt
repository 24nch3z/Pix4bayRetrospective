package ru.s4nchez.pix4bayretrospective.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.s4nchez.pix4bayretrospective.utils.GlideApp

fun ImageView.load(url: String, completeCallback: (isSuccess: Boolean) -> Unit) {
    GlideApp.with(this)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    completeCallback(false)
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    completeCallback(true)
                    return false
                }
            })
            .into(this)
}