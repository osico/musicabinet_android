package com.musicabinet.mobile.ui.courses

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse
import com.musicabinet.mobile.ui.courses.adapter.CourseAdapter
import com.musicabinet.mobile.ui.lesson.list.LessonListActivity
import com.musicabinet.mobile.utils.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.activity_courses.*

/**
 * @author Kirchhoff-
 */
class CoursesActivity : AppCompatActivity(), CoursesContract.View, BaseRecyclerAdapter.OnItemClickListener<InstrumentCourse> {

    companion object {
        const val INSTRUMENT_ID_ARG = "INSTRUMENT_ID_ARG"
        const val INSTRUMENT_NAME_ARG = "INSTRUMENT_NAME_ARG"
    }

    private lateinit var coursesAdapter: CourseAdapter
    private lateinit var presenter: CoursesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(INSTRUMENT_NAME_ARG)


        recyclerView.setVisible(false)
        tvError.setVisible(false)
        progressBar.setVisible(true)

        presenter = CoursePresenter(this, Injection.provideRepository())
        presenter.loadInstrumentMatrix(intent.getStringExtra(INSTRUMENT_ID_ARG))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }

    override fun showLoading(visible: Boolean) {
        progressBar.setVisible(visible)
    }

    override fun showCourseError(visible: Boolean) {
        tvError.setVisible(visible)
    }

    override fun showCourseList(visible: Boolean) {
        recyclerView.setVisible(visible)
    }

    override fun showBuyButton(visible: Boolean) {
        bBuy.setVisible(visible)
    }

    override fun showCourses(list: List<InstrumentCourse>) {

        coursesAdapter = CourseAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
        val divider = DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.white_divider)!!)
        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = coursesAdapter
        coursesAdapter.setOnItemClickListener(this)
    }

    override fun onItemClick(item: InstrumentCourse) {
        val intent = Intent(this, LessonListActivity::class.java)
        intent.putExtra(LessonListActivity.INSTRUMENT_COURSE_ARG, item)
        intent.putExtra(LessonListActivity.INSTRUMENT_ID_ARG, this.intent.getStringExtra(INSTRUMENT_ID_ARG))
        startActivity(intent)
    }

}