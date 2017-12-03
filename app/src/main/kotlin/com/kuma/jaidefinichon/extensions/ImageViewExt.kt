package com.kuma.jaidefinichon.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImageWith(imageUrl: String) {
    Glide.with(context)
            .asDrawable()
            .load(imageUrl)
            .transition(withCrossFade())
//            .apply(
//                    RequestOptions
//                            .placeholderOf(R.drawable.place_holder)
//                            .error(R.drawable.place_holder)
//                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//            )
            .into(this)
}