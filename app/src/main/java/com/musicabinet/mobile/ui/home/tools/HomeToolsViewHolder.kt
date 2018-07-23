package com.musicabinet.mobile.ui.home.tools

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.model.home.HomeToolsElement
import kotlinx.android.synthetic.main.item_home_tools.view.tvText
import kotlinx.android.synthetic.main.item_home_tools.view.tvTitle

/**
 * @author Kirchhoff-
 */
class HomeToolsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	fun bind(element: HomeToolsElement) {
		with(element) {
			itemView.tvTitle.text = title
			itemView.tvText.text = text
		}
	}
}