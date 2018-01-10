package com.musicabinet.mobile.ui.lessons.lesson.info

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.displayHtmlText
import com.musicabinet.mobile.ui.ActionBarActivity
import kotlinx.android.synthetic.main.activity_lesson_information.*

/**
 * @author Kirchhoff-
 */
class LessonInformationActivity : ActionBarActivity() {

    companion object {
        const val INFORMATION_ARG = "INFORMATION_ARG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_information)

        tvInformation.displayHtmlText(intent.getStringExtra(INFORMATION_ARG))
        tvInformation.movementMethod = ScrollingMovementMethod()
    }

}