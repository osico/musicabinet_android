package com.musicabinet.mobile.ui.lessons.lesson.info

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.displayHtmlText
import kotlinx.android.synthetic.main.activity_lesson_information.*

/**
 * @author Kirchhoff-
 */
class LessonInformationActivity : AppCompatActivity() {

    companion object {
        const val INFORMATION_ARG = "INFORMATION_ARG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_information)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvInformation.displayHtmlText(intent.getStringExtra(INFORMATION_ARG))
        tvInformation.movementMethod = ScrollingMovementMethod()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }
}