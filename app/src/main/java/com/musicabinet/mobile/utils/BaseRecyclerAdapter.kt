package com.musicabinet.mobile.utils

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.View
import java.util.*

/**
 * @author Kirchhoff-
 */

abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder, T>(items: List<T>)
    : RecyclerView.Adapter<VH>() {

    private val items = ArrayList<T>()

    private var onItemClickListener: OnItemClickListener<T>? = null

    private var onItemClickWithPositionListener: OnItemClickWithPositionListener<T>? = null

    private val clickListener = View.OnClickListener { view ->
        val position: Int = view.tag as Int
        val item = this.items[position]
        onItemClickListener?.onItemClick(item)
        onItemClickWithPositionListener?.onItemClick(item, position)
    }

    init {
        this.items.addAll(items)
    }

    fun add(value: T) {
        items.add(value)
        notifyDataSetChanged()
    }

    fun addItems(values: List<T>) {
        items.addAll(values)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(clickListener)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>) {
        this.onItemClickListener = onItemClickListener
    }

    protected fun setOnItemClickWithPosition(onItemClickWithPositionListener: OnItemClickWithPositionListener<T>) {
        this.onItemClickWithPositionListener = onItemClickWithPositionListener
    }

    fun getItem(position: Int): T {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getDataSet() = items

    interface OnItemClickListener<in T> {
        fun onItemClick(item: T)
    }

    interface OnItemClickWithPositionListener<in T> {
        fun onItemClick(item: T, position: Int)
    }
}