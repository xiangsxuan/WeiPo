package moe.tlaster.weipo.common.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

fun ImageView.load(source: String, customAction:(RequestBuilder<Drawable>.() -> Unit)? = null) {
    Glide.with(this).load(source).centerCrop().also {
        customAction?.invoke(it)
    }.into(this)
}