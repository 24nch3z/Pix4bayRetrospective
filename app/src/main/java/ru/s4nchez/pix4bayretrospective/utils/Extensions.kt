package ru.s4nchez.pix4bayretrospective.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun View.visibilityByFlag(flag: Boolean) {
    this.visibility = if (flag) View.VISIBLE else View.GONE
}

fun ImageView.load(context: Context, url: String, completeCallback: (isSuccess: Boolean) -> Unit) {
    GlideApp.with(context)
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

fun Fragment.shortResToast(@StringRes res: Int) {
    if (this.context != null) Toast.makeText(this.context, res, Toast.LENGTH_SHORT).show()
}