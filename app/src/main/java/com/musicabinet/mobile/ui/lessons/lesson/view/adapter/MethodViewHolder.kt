package com.musicabinet.mobile.ui.lessons.lesson.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.model.lesson.local.MethodItem
import kotlinx.android.synthetic.main.item_method.view.*

/**
 * @author Kirchhoff-
 */
class MethodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(element: MethodItem) {
        with(element) {
            itemView.tvDescription.text = description
        }
    }
}