package com.musicabinet.mobile.ui.home.tools

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.home.HomeToolsElement
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class HomeToolsAdapter(items: List<HomeToolsElement>) : BaseRecyclerAdapter<HomeToolsViewHolder, HomeToolsElement>(items) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		HomeToolsViewHolder(parent.inflate(R.layout.item_home_tools))

	override fun onBindViewHolder(holder: HomeToolsViewHolder, position: Int) {
		super.onBindViewHolder(holder, position)
		holder.bind(getItem(position))
	}
}