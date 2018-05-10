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
import com.musicabinet.mobile.ui.web.WebVideoActivity
import com.musicabinet.mobile.utils.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.view_home_tutorial.view.*

/**
 * @author Kirchhoff-
 */
class HomeTutorialView : FrameLayout, HomeTutorialContract.View, BaseRecyclerAdapter.OnItemClickListener<HomeDataElement> {

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
        cvLoading.setVisible(true)
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

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.loadItems()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.unsubscribe()
    }

    override fun showLoading(visible: Boolean) {
        cvLoading.setVisible(visible)
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

        homeTutorialAdapter?.setOnItemClickListener(this)
        recyclerView.setVisible(true)
    }

    override fun onItemClick(item: HomeDataElement) {
        presenter.onTutorialClick(item)
    }

    override fun openTutorial(url: String, title: String) {
        WebVideoActivity.startWebVideo(context, url, title)
    }
}