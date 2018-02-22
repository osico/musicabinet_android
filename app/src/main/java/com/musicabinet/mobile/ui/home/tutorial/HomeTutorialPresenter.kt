package com.musicabinet.mobile.ui.home.tutorial

/**
 * @author Kirchhoff-
 */
import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.home.HomeDataElement
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
    private var homeTutorialMaxSize = 1


    override fun loadItems() {

        if (homeTutorialLoaded < homeTutorialMaxSize) {
            subscriptions.add(repository.getHomeTutorial(homeTutorialLoaded)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        if (homeTutorialLoaded == 0)
                            view.showLoading(true)
                    }.doOnTerminate {
                view.showLoading(false)
            }.subscribe({ homeData: HomeData? ->
                if (homeData != null && !homeData.fields.isEmpty()) {

                    homeTutorialLoaded += homeData.partCount
                    homeTutorialMaxSize = homeData.totalCount

                    if (homeTutorialLoaded >= homeTutorialMaxSize)
                        view.disablePaginationLoading()

                    view.setHomeTutorialItem(homeData.fields, homeTutorialLoaded < homeTutorialMaxSize)

                } else {
                    view.showHomeTutorialError()
                }
            }, { view.showHomeTutorialError() }))
        }
    }

    override fun onTutorialClick(tutorialItem: HomeDataElement) {
        if (tutorialItem.dataField.url != null)
            view.openTutorial(tutorialItem.dataField.url!!, tutorialItem.name)
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

}