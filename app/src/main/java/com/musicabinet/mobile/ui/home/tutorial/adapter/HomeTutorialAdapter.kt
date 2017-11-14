package com.musicabinet.mobile.ui.home.tutorial.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class HomeTutorialAdapter(items: List<HomeDataElement>)
    : BaseRecyclerAdapter<HomeTutorialViewHolder, HomeDataElement>(items) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            HomeTutorialViewHolder(parent.inflate(R.layout.item_home_tutorial))


    override fun onBindViewHolder(holder: HomeTutorialViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }
}