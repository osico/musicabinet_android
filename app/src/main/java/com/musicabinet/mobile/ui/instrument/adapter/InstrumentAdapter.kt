package com.musicabinet.mobile.ui.instrument.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.instrument.InstrumentDataElement
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class InstrumentAdapter(items: List<InstrumentDataElement>)
    : BaseRecyclerAdapter<InstrumentViewHolder, InstrumentDataElement>(items) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            InstrumentViewHolder(parent.inflate(R.layout.item_instrument))

    override fun onBindViewHolder(holder: InstrumentViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }

}