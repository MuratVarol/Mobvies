package com.example.mobvies.extension

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.setImageByUrl(url)
}


@BindingAdapter("setBitmap")
fun setImageBitmap(imageView: ImageView, bitmap: Bitmap?) {

    bitmap?.let {
        imageView.setImageBitmap(bitmap)
    }
}
