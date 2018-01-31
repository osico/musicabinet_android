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
    private var listener: NoteItemSelected

    constructor(items: List<NoteElement>, selectedItem: NoteElement?, listener: NoteItemSelected) : super(items) {
        selectedList = MutableList(items.size, { false })
        setOnItemClickWithPosition(this)
        this.listener = listener

        if (selectedItem != null) {
            for (i in items.indices) {
                if (items[i].id == selectedItem.id) {
                    selectedList[i] = true
                    listener.onItemSelected(true)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NoteImageViewHolder(parent.inflate(R.layout.item_note_image))

    override fun onBindViewHolder(holder: NoteImageViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position), selectedList[position])
    }

    override fun onItemClick(item: NoteElement, position: Int) {
        val result = !selectedList[position]
        listener.onItemSelected(result)
        selectedList = MutableList(getDataSet().size, { false })
        selectedList[position] = result
        notifyDataSetChanged()
    }

    fun getSelectedElement(): NoteElement {
        var position = 0
        for (i in selectedList.indices) {
            if (selectedList[i])
                position = i
        }

        return getItem(position)
    }

    fun getSelectedElementPosition(): Int {
        var position = 0
        for (i in selectedList.indices) {
            if (selectedList[i])
                position = i
        }

        return position
    }

    interface NoteItemSelected {

        fun onItemSelected(isSelected: Boolean)
    }
}