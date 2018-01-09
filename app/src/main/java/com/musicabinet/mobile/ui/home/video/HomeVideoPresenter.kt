package com.musicabinet.mobile.ui.home.video

import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Kirchhoff-
 */
class HomeVideoPresenter(private val repository: MusicabinetRepository,
                         private val view: HomeVideoContract.View) : HomeVideoContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private var homeVideoLoaded = 0
    private var homeVideoMaxSize = 1


    override fun loadItems() {

        if (homeVideoLoaded < homeVideoMaxSize) {
            subscriptions.add(repository.getHomeVideo(homeVideoLoaded)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        if (homeVideoLoaded == 0)
                            view.showLoading(true)
                    }.doOnTerminate {
                view.showLoading(false)
            }.subscribe({ homeData: HomeData? ->
                if (homeData != null && !homeData.fields.isEmpty()) {

                    homeVideoLoaded += homeData.partCount
                    homeVideoMaxSize = homeData.totalCount

                    if (homeVideoLoaded >= homeVideoMaxSize)
                        view.disablePaginationLoading()

                    view.setHomeVideoItem(homeData.fields, homeVideoLoaded < homeVideoMaxSize)
                } else {
                    view.showHomeVideoError()
                }
            }, { t: Throwable? ->
                view.showHomeVideoError()
            }))
        }
    }

    override fun onVideoClick(videoItem: HomeDataElement) {
        if (videoItem.dataField.url != null)
            view.openVideo(videoItem.dataField.url!!, videoItem.name)
    }

}