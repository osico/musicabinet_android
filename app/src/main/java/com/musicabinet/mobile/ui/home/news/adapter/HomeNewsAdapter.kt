package com.musicabinet.mobile.ui.home.news.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class HomeNewsAdapter(items: List<HomeDataElement>)
    : BaseRecyclerAdapter<HomeNewsViewHolder, HomeDataElement>(items) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            HomeNewsViewHolder(parent.inflate(R.layout.item_home_news))

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }

}