package com.musicabinet.mobile.ui.lessons.lesson.note.adapter.image

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class NoteImageAdapter(items: List<NoteElement>)
    : BaseRecyclerAdapter<NoteImageViewHolder, NoteElement>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NoteImageViewHolder(parent.inflate(R.layout.item_note_image))

    override fun onBindViewHolder(holder: NoteImageViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }

}