package com.musicabinet.mobile.ui.lessons.lesson.note.adapter.image

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class NoteImageAdapter : BaseRecyclerAdapter<NoteImageViewHolder, NoteElement>,
        BaseRecyclerAdapter.OnItemClickWithPositionListener<NoteElement> {

    private var selectedList: MutableList<Boolean>

    constructor(items: List<NoteElement>) : super(items) {
        selectedList = MutableList(items.size, { false })
        setOnItemClickWithPosition(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NoteImageViewHolder(parent.inflate(R.layout.item_note_image))

    override fun onBindViewHolder(holder: NoteImageViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position), selectedList[position])
    }

    override fun onItemClick(item: NoteElement, position: Int) {
        val result = !selectedList[position]
        selectedList = MutableList(getDataSet().size, { false })
        selectedList[position] = result
        notifyDataSetChanged()
    }
}