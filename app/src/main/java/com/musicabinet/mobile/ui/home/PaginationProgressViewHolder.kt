package com.musicabinet.mobile.ui.home

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.extensions.setVisible
import kotlinx.android.synthetic.main.item_home_pagination.view.*

/**
 * @author Kirchhoff-
 */
class PaginationProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(visible: Boolean) {
        itemView.paginationProgress.setVisible(visible)
    }
}