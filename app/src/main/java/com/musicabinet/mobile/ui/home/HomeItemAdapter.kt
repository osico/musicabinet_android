package com.musicabinet.mobile.ui.home

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.ui.home.news.HomeNewsViewHolder
import com.musicabinet.mobile.ui.home.tutorial.HomeTutorialViewHolder
import com.musicabinet.mobile.ui.home.video.HomeVideoViewHolder
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 *
 * General adapter for accumulate all logic for different type of view in HomeScreen.
 */
class HomeItemAdapter(items: List<HomeDataElement>, @LayoutRes private val itemLayout: Int)
    : BaseRecyclerAdapter<RecyclerView.ViewHolder, HomeDataElement>(items) {

    private var shouldShowPaginationProgress = true

    companion object {
        const val ITEM_FOOTER = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_FOOTER) {

            return PaginationProgressViewHolder(parent.inflate(R.layout.item_home_pagination))
        }

        return when (itemLayout) {
            R.layout.item_home_news -> HomeNewsViewHolder(parent.inflate(R.layout.item_home_news))
            R.layout.item_home_tutorial -> HomeTutorialViewHolder(parent.inflate(R.layout.item_home_tutorial))
            R.layout.item_home_video -> HomeVideoViewHolder(parent.inflate(R.layout.item_home_video))
            else -> HomeNewsViewHolder(parent.inflate(R.layout.item_home_news))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        if (holder is HomeNewsViewHolder)
            holder.bind(getItem(position))
        if (holder is HomeTutorialViewHolder)
            holder.bind(getItem(position))
        if (holder is HomeVideoViewHolder)
            holder.bind(getItem(position))
        else (holder as? PaginationProgressViewHolder)?.bind(shouldShowPaginationProgress)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    fun disablePaginationLoading() {
        shouldShowPaginationProgress = false
    }

    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1)
            return ITEM_FOOTER

        return super.getItemViewType(position)
    }
}