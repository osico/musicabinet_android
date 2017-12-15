package com.musicabinet.mobile.ui.lessons.lesson.view.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.lesson.local.MethodItem
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class MethodViewAdapter(items: List<MethodItem>)
    : BaseRecyclerAdapter<MethodViewHolder, MethodItem>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MethodViewHolder(parent.inflate(R.layout.item_method))

    override fun onBindViewHolder(holder: MethodViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }
}