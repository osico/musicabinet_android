package com.musicabinet.mobile.ui.home.tutorial

/**
 * @author Kirchhoff-
 */
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.ui.home.tutorial.adapter.HomeTutorialAdapter
import kotlinx.android.synthetic.main.view_home_tutorial.view.*

/**
 * @author Kirchhoff-
 */
class HomeTutorialView : FrameLayout, HomeTutorialContract.View {

    private var homeTutorialAdapter: HomeTutorialAdapter? = null
    private lateinit var presenter: HomeTutorialContract.Presenter
    private var loading: Boolean = false
    private val linearLayoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_home_tutorial, this, true)

        presenter = HomeTutorialPresenter(Injection.provideRepository(), this)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.setVisible(false)
        cvError.setVisible(false)
        progressBar.setVisible(true)
        presenter.loadItems()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!loading && linearLayoutManager.itemCount
                        <= (linearLayoutManager.findLastVisibleItemPosition() + 1)) {
                    loading = true
                    presenter.loadItems()
                }
            }
        })
    }

    override fun showLoading(visible: Boolean) {
        progressBar.setVisible(visible)
    }

    override fun disablePaginationLoading() {
        homeTutorialAdapter?.disablePaginationLoading()
    }

    override fun showHomeTutorialError() {
        cvError.setVisible(true)
    }

    override fun showHomeTutorialList(visible: Boolean) {
        recyclerView.setVisible(visible)
    }

    override fun setHomeTutorialItem(videoList: List<HomeDataElement>, enablePagination: Boolean) {
        if (homeTutorialAdapter == null) {
            homeTutorialAdapter = HomeTutorialAdapter(videoList, enablePagination)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = homeTutorialAdapter
        } else {
            homeTutorialAdapter?.addItems(videoList)
            loading = false
        }

        recyclerView.setVisible(true)
    }

}