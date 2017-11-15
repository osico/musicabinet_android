package com.musicabinet.mobile.ui.instrument.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setColorText
import com.musicabinet.mobile.model.instrument.InstrumentDataElement
import kotlinx.android.synthetic.main.item_instrument.view.*

/**
 * @author Kirchhoff-
 */
class InstrumentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(instrument: InstrumentDataElement) {
        with(instrument) {
            itemView.tvName.text = nameLocalized
            if (active)
                itemView.tvName.setColorText(R.color.white)
            else
                itemView.tvName.setColorText(R.color.red)
        }
    }

}