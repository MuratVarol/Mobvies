package com.example.mobvies.base

import com.example.mobvies.util.listener.ItemClickListener
import com.example.mobvies.view.base.BaseRecyclerAdapter

class AdapterBuilder<ModelType>(
    val itemList: List<ModelType>,
    val layoutId: Int,
    val itemClickListener: ItemClickListener<ModelType>?
) {

    fun build(): BaseRecyclerAdapter<ModelType> {
        val baseAdapter = BaseRecyclerAdapter(itemList, layoutId, itemClickListener)
        baseAdapter.updateData(itemList)
        return baseAdapter
    }


}