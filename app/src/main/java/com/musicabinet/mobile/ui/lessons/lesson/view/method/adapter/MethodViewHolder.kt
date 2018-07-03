package com.musicabinet.mobile.ui.lessons.lesson.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.local.MethodItem
import kotlinx.android.synthetic.main.item_method.view.bReadNow
import kotlinx.android.synthetic.main.item_method.view.tvDescription
import kotlinx.android.synthetic.main.item_method.view.tvMethodName
import kotlinx.android.synthetic.main.item_method.view.tvPage
import kotlinx.android.synthetic.main.item_method.view.videoLayout

/**
 * @author Kirchhoff-
 */
class MethodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	fun bind(element: MethodItem, listener: OnMethodItemClickListener) {
		with(element) {

			itemView.tvDescription.text = description
			itemView.tvMethodName.text = name
			itemView.videoLayout.setVisible(element.video != null)
			itemView.videoLayout.setOnClickListener { listener.onVideoClick(element) }

			itemView.bReadNow.setVisible(element.information != null)
			itemView.bReadNow.setOnClickListener {
				if (element.information != null)
					listener.onInfoClick(element.information)
			}

			itemView.tvPage.text = itemView.resources.getString(R.string.method_page_format, page)

		}
	}


	interface OnMethodItemClickListener {

		fun onInfoClick(information: String)

		fun onVideoClick(methodItem: MethodItem)
	}
}