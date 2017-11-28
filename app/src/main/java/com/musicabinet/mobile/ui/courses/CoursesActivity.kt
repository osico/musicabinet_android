package com.musicabinet.mobile.ui.courses

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class CoursesActivity : AppCompatActivity(), CoursesContract.View {

    companion object {
        const val INSTRUMENT_ID_ARG = "INSTRUMENT_ID_ARG"
        const val INSTRUMENT_NAME_ARG = "INSTRUMENT_NAME_ARG"
    }

    private lateinit var presenter: CoursesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(INSTRUMENT_NAME_ARG)

        presenter = CoursePresenter(this, Injection.provideRepository())

        presenter.loadInstrumentMatrix(intent.getStringExtra(INSTRUMENT_ID_ARG))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }

    override fun showSuccess(list: List<InstrumentCourse>) {
        toast("Success")
        for (item in list) {
            Log.d("TAG", "Name = " + item.name + " Price = " + item.productPrice +
                    ", Size = " + item.lessonGroups.size);
        }
    }

    override fun showError() {
        toast("Error")
    }

}