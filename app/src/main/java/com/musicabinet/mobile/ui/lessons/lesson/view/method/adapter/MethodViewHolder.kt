package com.musicabinet.mobile.ui.lessons.lesson.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.local.MethodItem
import kotlinx.android.synthetic.main.item_method.view.*

/**
 * @author Kirchhoff-
 */
class MethodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(element: MethodItem, listener: OnMethodItemClickListener) {
        with(element) {
            itemView.tvDescription.text = description
            itemView.fabInfo.setVisible(element.information != null)
            itemView.fabInfo.setOnClickListener {
                if (element.information != null)
                    listener.onInfoClick(element.information)
            }

            itemView.setOnClickListener { listener.onVideoClick(element) }
        }
    }


    interface OnMethodItemClickListener {

        fun onInfoClick(information: String)

        fun onVideoClick(methodItem: MethodItem)
    }
}