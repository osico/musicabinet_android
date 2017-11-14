package com.musicabinet.mobile.ui.home.video.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.home.HomeDataElement
import kotlinx.android.synthetic.main.item_home_video.view.*

/**
 * @author Kirchhoff-
 */
class HomeVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(element: HomeDataElement) {
        with(element) {
            itemView.ivTutorial.setImageResource(R.drawable.ic_musicabinet)
        }
    }
}