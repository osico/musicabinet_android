package com.musicabinet.mobile.ui.home.video.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class HomeVideoAdapter(items: List<HomeDataElement>)
    : BaseRecyclerAdapter<HomeVideoViewHolder, HomeDataElement>(items) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            HomeVideoViewHolder(parent.inflate(R.layout.item_home_video))


    override fun onBindViewHolder(holder: HomeVideoViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }
}