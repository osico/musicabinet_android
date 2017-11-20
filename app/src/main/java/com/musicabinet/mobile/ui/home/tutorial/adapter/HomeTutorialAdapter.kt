package com.musicabinet.mobile.ui.home.tutorial.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.ui.home.PaginationProgressViewHolder
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class HomeTutorialAdapter(items: List<HomeDataElement>, private var shouldShowPaginationProgress: Boolean = true)
    : BaseRecyclerAdapter<RecyclerView.ViewHolder, HomeDataElement>(items) {

    private val ITEM_FOOTER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_FOOTER) {

            return PaginationProgressViewHolder(parent.inflate(R.layout.item_home_pagination))
        }

        return HomeTutorialViewHolder(parent.inflate(R.layout.item_home_tutorial))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        if (holder is HomeTutorialViewHolder)
            holder.bind(getItem(position))
        else if (holder is PaginationProgressViewHolder)
            holder.bind(shouldShowPaginationProgress)
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