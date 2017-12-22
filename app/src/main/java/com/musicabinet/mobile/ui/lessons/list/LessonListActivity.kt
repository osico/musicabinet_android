package com.musicabinet.mobile.ui.lessons.list

import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.extensions.textColor
import com.musicabinet.mobile.model.instrument.matrix.LessonItem
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentLessonList
import com.musicabinet.mobile.ui.lessons.lesson.LessonActivity
import com.musicabinet.mobile.ui.lessons.list.pager.LessonListPagerAdapter
import com.musicabinet.mobile.ui.lessons.list.pager.LessonListView
import com.musicabinet.mobile.ui.view.LoadingDialog
import kotlinx.android.synthetic.main.activity_lesson_list.*
import org.jetbrains.anko.toast


/**
 * @author Kirchhoff-
 */
class LessonListActivity : AppCompatActivity(), LessonListContract.View, LessonListView.LessonBuyButtonListener {

    companion object {
        const val INSTRUMENT_COURSE_ARG = "INSTRUMENT_COURSE_ARG"
        const val INSTRUMENT_ID_ARG = "INSTRUMENT_ID_ARG"
    }

    private lateinit var instrumentCourse: InstrumentCourse
    private lateinit var presenter: LessonListContract.Presenter
    private lateinit var adapter: LessonListPagerAdapter

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        instrumentCourse = intent
                .getSerializableExtra(LessonListActivity.INSTRUMENT_COURSE_ARG) as InstrumentCourse

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = instrumentCourse.name

        lessonLayout.setVisible(false)

        presenter = LessonListPresenter(this, Injection.provideRepository(), instrumentCourse)
        presenter.getFilters(intent.getStringExtra(INSTRUMENT_ID_ARG))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }

    override fun showLoading(visible: Boolean) {
        progressBar.setVisible(visible)
    }

    override fun showLessonFilter(list: List<InstrumentLessonList>) {
        progressBar.setVisible(false)

        adapter = LessonListPagerAdapter(this, list, instrumentCourse, this)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        lessonLayout.setVisible(true)
    }

    override fun showError() {
        toast("Error")
    }

    override fun showSuccess() {
        toast("Success")
    }

    override fun onBuyButtonClick() {
        presenter.buyLesson(instrumentCourse.id)
    }

    override fun onItemClick(item: LessonItem) {
        presenter.onLessonClick(item)
    }

    override fun showBuyLoading(show: Boolean) {
        loadingDialog?.dismiss()

        if (show) {
            loadingDialog = LoadingDialog()
            loadingDialog?.show(supportFragmentManager, "TAG")
        }
    }

    override fun showPaymentDialog() {
        val title = getString(R.string.buy_dialog_title)
        val foregroundColorSpan = ForegroundColorSpan(Color.BLACK)
        val ssBuilder = SpannableStringBuilder(title)
        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                title.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        val alertDialog = AlertDialog.Builder(this)
                .setTitle(ssBuilder)
                .setMessage(R.string.buy_dialog_text)
                .setPositiveButton(R.string.go_to_site, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val browserIntent = Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://app.musicabinet.com"))
                        startActivity(browserIntent)
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create()

        alertDialog.show()
        alertDialog.getButton(BUTTON_NEGATIVE).textColor(R.color.colorPrimary)
        alertDialog.getButton(BUTTON_POSITIVE).textColor(R.color.colorPrimary)
    }

    override fun moveToLesson(id: String) {
        val intent = Intent(this, LessonActivity::class.java)
        intent.putExtra(LessonActivity.LESSON_ID_ARG, id)
        startActivity(intent)
    }

    override fun showSuccessPayment() {
        instrumentCourse.productAvailable = true
        instrumentCourse.productActive = true
        adapter.notifyDataSetChanged()
    }
}