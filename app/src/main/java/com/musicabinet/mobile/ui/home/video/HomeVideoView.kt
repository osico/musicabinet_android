package com.musicabinet.mobile.ui.home.video

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.ui.home.video.adapter.HomeVideoAdapter
import com.musicabinet.mobile.utils.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.view_home_video.view.*

/**
 * @author Kirchhoff-
 */
class HomeVideoView : FrameLayout, HomeVideoContract.View, BaseRecyclerAdapter.OnItemClickListener<HomeDataElement> {

    private var homeVideoAdapter: HomeVideoAdapter? = null
    private lateinit var presenter: HomeVideoContract.Presenter
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
        LayoutInflater.from(context).inflate(R.layout.view_home_video, this, true)

        presenter = HomeVideoPresenter(Injection.provideRepository(), this)

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

    override fun showLoading(visible: Boolean) {
        cvLoading.setVisible(visible)
    }

    override fun disablePaginationLoading() {
        homeVideoAdapter?.disablePaginationLoading()
    }

    override fun showHomeVideoError() {
        cvError.setVisible(true)
    }

    override fun showHomeVideoList(visible: Boolean) {
        recyclerView.setVisible(visible)
    }

    override fun setHomeVideoItem(videoList: List<HomeDataElement>, enablePagination: Boolean) {
        if (homeVideoAdapter == null) {
            homeVideoAdapter = HomeVideoAdapter(videoList, enablePagination)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = homeVideoAdapter
        } else {
            homeVideoAdapter?.addItems(videoList)
            loading = false
        }

        recyclerView.setVisible(true)
        homeVideoAdapter?.setOnItemClickListener(this)
    }

    override fun onItemClick(item: HomeDataElement) {
        presenter.onVideoClick(item)
    }

    override fun openVideo(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setDataAndType(Uri.parse(url), "video/mp4")

        val packageManager = context.packageManager
        val infos = packageManager.queryIntentActivities(intent, 0)

        if (infos.size > 0) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, R.string.error_open_video, Toast.LENGTH_LONG).show()
        }

    }
}