package com.musicabinet.mobile.ui.lessons.lesson.note.adapter.image

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.extensions.loadLessonImage
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement
import kotlinx.android.synthetic.main.item_note_image.view.*

/**
 * @author Kirchhoff-
 */
class NoteImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(element: NoteElement) {
        with(element) {
            itemView.ivNote.loadLessonImage(image.id)
        }
    }
}