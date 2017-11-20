package com.musicabinet.mobile.ui.home.news

/**
 * @author Kirchhoff-
 */
import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Kirchhoff-
 */
class HomeNewsPresenter(private val repository: MusicabinetRepository,
                        private val view: HomeNewsContract.View) : HomeNewsContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private var homeNewsLoaded = 0
    private var homeNewsMaxSize = 1


    override fun loadItems() {

        if (homeNewsLoaded < homeNewsMaxSize) {
            subscriptions.add(repository.getHomeNews(homeNewsLoaded)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        if (homeNewsLoaded == 0)
                            view.showLoading(true)
                    }.doOnTerminate {
                view.showLoading(false)
            }.subscribe({ homeData: HomeData? ->
                if (homeData != null && !homeData.fields.isEmpty()) {

                    homeNewsLoaded += homeData.partCount
                    homeNewsMaxSize = homeData.totalCount

                    if (homeNewsLoaded >= homeNewsMaxSize)
                        view.disablePaginationLoading()

                    view.setHomeNewsItem(homeData.fields, homeNewsLoaded < homeNewsMaxSize)
                } else {
                    view.showHomeNewsError()
                }
            }, { t: Throwable? ->
                view.showHomeNewsError()
            }))
        }
    }

}