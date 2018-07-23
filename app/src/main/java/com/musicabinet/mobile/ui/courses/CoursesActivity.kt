package com.musicabinet.mobile.ui.courses

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.createPaymentDialog
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse
import com.musicabinet.mobile.ui.ActionBarActivity
import com.musicabinet.mobile.ui.courses.adapter.CourseAdapter
import com.musicabinet.mobile.ui.lessons.list.LessonListActivityArgs
import com.musicabinet.mobile.utils.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.activity_courses.*

/**
 * @author Kirchhoff-
 */
class CoursesActivity : ActionBarActivity(), CoursesContract.View, BaseRecyclerAdapter.OnItemClickListener<InstrumentCourse> {

    companion object {
        const val INSTRUMENT_ID_ARG = "INSTRUMENT_ID_ARG"
        const val INSTRUMENT_NAME_ARG = "INSTRUMENT_NAME_ARG"
    }

    private lateinit var coursesAdapter: CourseAdapter
    private lateinit var presenter: CoursesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        title = intent.getStringExtra(INSTRUMENT_NAME_ARG)

        recyclerView.setVisible(false)
        tvError.setVisible(false)
        progressBar.setVisible(true)
        bBuy.setOnClickListener { presenter.onBuyClick() }

        presenter = CoursePresenter(this, Injection.provideRepository())
        presenter.loadInstrumentMatrix(intent.getStringExtra(INSTRUMENT_ID_ARG))
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
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

    override fun showPaymentDialog() {
        createPaymentDialog(intent.getStringExtra(INSTRUMENT_ID_ARG))
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
        LessonListActivityArgs(item,
                this.intent.getStringExtra(INSTRUMENT_ID_ARG),
                this.intent.getStringExtra(INSTRUMENT_NAME_ARG)).launch(this)
    }

}