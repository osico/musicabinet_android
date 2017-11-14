package com.musicabinet.mobile.ui.home.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.model.home.HomeDataElement
import kotlinx.android.synthetic.main.item_home_news.view.*

/**
 * @author Kirchhoff-
 */
class HomeNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(element: HomeDataElement) {
        with(element) {
            itemView.tvTitle.text = name
            itemView.tvText.text = dataField.text
            itemView.tvDate.text = date
        }
    }
}