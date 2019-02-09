package com.example.mobvies.util.listener

import android.view.View

interface ItemClickListener<ModelType> {
    fun onItemClick(view: View, item: ModelType, position: Int)
}