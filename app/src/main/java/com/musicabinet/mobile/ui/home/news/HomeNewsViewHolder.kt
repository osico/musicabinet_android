package com.musicabinet.mobile.ui.home.news

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.utils.DateUtils
import kotlinx.android.synthetic.main.item_home_news.view.*

/**
 * @author Kirchhoff-
 */
class HomeNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(element: HomeDataElement) {
        with(element) {
            itemView.tvTitle.text = name
            itemView.tvText.text = dataField.text
            itemView.tvDate.text = DateUtils.parseIso8601DateTime(date)

            if (dataField.hasBorder())
                itemView.homeNewsRootLayout.background = ContextCompat.getDrawable(itemView.context,
                        R.drawable.green_stroke_background)
            else
                itemView.homeNewsRootLayout.background = null
        }
    }
}