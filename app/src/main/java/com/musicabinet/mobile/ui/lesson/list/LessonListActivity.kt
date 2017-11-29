package com.musicabinet.mobile.ui.lesson.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class LessonListActivity : AppCompatActivity(), LessonListContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }
}