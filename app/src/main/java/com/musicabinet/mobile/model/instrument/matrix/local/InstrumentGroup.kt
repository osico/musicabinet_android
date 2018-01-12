package com.musicabinet.mobile.model.instrument.matrix.local

import android.annotation.SuppressLint
import android.os.Parcelable
import com.musicabinet.mobile.model.instrument.matrix.LessonItem
import com.musicabinet.mobile.model.instrument.matrix.Modules
import kotlinx.android.parcel.Parcelize

/**
 * @author Kirchhoff-
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class InstrumentGroup(val lessonList: List<LessonItem>, val module: Modules) : Parcelable {

    fun isContainsFilter(filterId: String): Boolean {
        if (lessonList.isEmpty())
            return false

        for (lesson in lessonList) {

            if (lesson.programs.isEmpty())
                return false

            for (program in lesson.programs) {
                if (program == filterId)
                    return true
            }

        }

        return false
    }


    fun filteredInstrumentGroup(filterId: String): InstrumentGroup {
        val bufLessonList = ArrayList<LessonItem>()

        for (lesson in lessonList) {

            for (program in lesson.programs) {
                if (program == filterId)
                    bufLessonList.add(lesson)
            }

        }


        return InstrumentGroup(bufLessonList, module)
    }

}