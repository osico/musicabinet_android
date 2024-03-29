package com.musicabinet.mobile.ui.lessons.list

import android.content.Intent
import android.os.Bundle
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.createPaymentDialog
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.extensions.toast
import com.musicabinet.mobile.model.instrument.matrix.LessonItem
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentLessonList
import com.musicabinet.mobile.ui.ActionBarActivity
import com.musicabinet.mobile.ui.lessons.lesson.LessonActivity
import com.musicabinet.mobile.ui.lessons.list.pager.LessonListPagerAdapter
import com.musicabinet.mobile.ui.lessons.list.pager.LessonListView
import com.musicabinet.mobile.ui.view.LoadingDialog
import kotlinx.android.synthetic.main.activity_lesson_list.*

/**
 * @author Kirchhoff-
 */
class LessonListActivity : ActionBarActivity(), LessonListContract.View, LessonListView.LessonBuyButtonListener {

    private val args by lazy {
        LessonListActivityArgs.deserializeFrom(intent)
    }

    private lateinit var presenter: LessonListContract.Presenter
    private lateinit var adapter: LessonListPagerAdapter

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        title = args.instrumentName

        lessonLayout.setVisible(false)

        presenter = LessonListPresenter(this, Injection.provideRepository(),
                Injection.provideStorage(), args.instrumentCourse)
        presenter.getFilters(args.instrumentId)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun showLoading(visible: Boolean) {
        progressBar.setVisible(visible)
    }

    override fun showLessonFilter(list: List<InstrumentLessonList>) {
        progressBar.setVisible(false)

        adapter = LessonListPagerAdapter(this, list, args.instrumentCourse, this)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        lessonLayout.setVisible(true)
    }

    override fun showError() {
        toast(R.string.internal_error)
    }

    override fun showSuccess() {
        toast(R.string.success)
    }

    override fun showNotAvailableError() {
        toast(R.string.product_not_available)
    }

    override fun onBuyButtonClick() {
        presenter.buyLesson(args.instrumentCourse.id)
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
        createPaymentDialog(args.instrumentId)
    }

    override fun showAuthorizedError() {
        toast(R.string.authorize_error)
    }

    override fun moveToLesson(id: String) {
        val intent = Intent(this, LessonActivity::class.java)
        intent.putExtra(LessonActivity.LESSON_ID_ARG, id)
        startActivity(intent)
    }

    override fun showSuccessPayment() {
        args.instrumentCourse.productAvailable = true
        args.instrumentCourse.productActive = true
        adapter.notifyDataSetChanged()
    }
}