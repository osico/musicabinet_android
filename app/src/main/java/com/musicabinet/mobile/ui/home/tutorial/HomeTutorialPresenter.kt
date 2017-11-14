package com.musicabinet.mobile.ui.home.tutorial

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
class HomeTutorialPresenter(private val repository: MusicabinetRepository,
                            private val view: HomeTutorialContract.View) : HomeTutorialContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private var homeTutorialLoaded = 0
    private var homeTutorialMaxSize = 0


    override fun loadItems() {

        subscriptions.add(repository.getHomeTutorial(homeTutorialLoaded)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    if (homeTutorialLoaded == 0)
                        view.showLoading(true)
                    else
                        view.showPaginationLoading(true)
                }.doOnTerminate {
            view.showLoading(false)
            view.showPaginationLoading(false)
        }.subscribe({ homeData: HomeData? ->
            if (homeData != null && !homeData.fields.isEmpty()) {
                view.setHomeTutorialItem(homeData.fields)
                homeTutorialLoaded = homeData.partCount
                homeTutorialMaxSize = homeData.totalCount
            } else {
                view.showHomeTutorialError()
            }
        }, { t: Throwable? ->
            view.showHomeTutorialError()
        }))
    }

}