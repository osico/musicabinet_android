package com.musicabinet.mobile.ui.lessons.list

import android.content.Context
import android.content.Intent
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse
import com.musicabinet.mobile.ui.ActivityArgs

/**
 * @author Kirchhoff-
 *
 * Arguments to launch [LessonListActivity]
 */
data class LessonListActivityArgs(val instrumentCourse: InstrumentCourse,
                                  val instrumentId: String,
                                  val instrumentName: String) : ActivityArgs {

    override fun intent(activity: Context): Intent =
            Intent(activity, LessonListActivity::class.java).apply {
                putExtra(COURSE_KEY, instrumentCourse)
                putExtra(NAME_KEY, instrumentName)
                putExtra(ID_KEY, instrumentId)
            }


    companion object {
        fun deserializeFrom(intent: Intent): LessonListActivityArgs {
            return LessonListActivityArgs(
                    instrumentCourse = intent.getParcelableExtra(COURSE_KEY),
                    instrumentName = intent.getStringExtra(NAME_KEY),
                    instrumentId = intent.getStringExtra(ID_KEY)
            )
        }
    }
}

private const val COURSE_KEY = "INSTRUMENT_COURSE_ARG"
private const val NAME_KEY = "INSTRUMENT_NAME_ARG"
private const val ID_KEY = "INSTRUMENT_ID_ARG"