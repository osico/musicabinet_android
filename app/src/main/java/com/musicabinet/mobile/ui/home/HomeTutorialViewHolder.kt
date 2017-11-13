package com.musicabinet.mobile.ui.home

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.model.home.HomeDataElement
import kotlinx.android.synthetic.main.item_home_tutorial.view.*

/**
 * @author Kirchhoff-
 */
class HomeTutorialViewHolder(iteView: View) : RecyclerView.ViewHolder(iteView) {

    fun bind(element: HomeDataElement) {
        with(element) {
            itemView.tvTitle.text = name
            itemView.tvText.text = dataField.text
        }
    }
}