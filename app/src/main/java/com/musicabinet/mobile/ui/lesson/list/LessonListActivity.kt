package com.musicabinet.mobile.ui.lesson.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterItem
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse
import kotlinx.android.synthetic.main.activity_lesson_list.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class LessonListActivity : AppCompatActivity(), LessonListContract.View {

    companion object {
        const val INSTRUMENT_COURSE_ARG = "INSTRUMENT_COURSE_ARG"
    }

    private lateinit var instrumentCourse: InstrumentCourse
    private lateinit var presenter: LessonListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        instrumentCourse = intent
                .getSerializableExtra(LessonListActivity.INSTRUMENT_COURSE_ARG) as InstrumentCourse

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = instrumentCourse.name

        lessonLayout.setVisible(false)

        presenter = LessonListPresenter(this, Injection.provideRepository())
        presenter.getFilters(instrumentCourse.id)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }

    override fun showLoading(visible: Boolean) {
        progressBar.setVisible(visible)
    }

    override fun showLessonFilter(list: List<InstrumentFilterItem>) {
        progressBar.setVisible(false)

        viewPager.adapter = LessonListPagerAdapter(this, list)
        tabLayout.setupWithViewPager(viewPager)
        lessonLayout.setVisible(true)
    }

    override fun showError() {
        toast("Error")
    }
}