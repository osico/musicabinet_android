package com.musicabinet.mobile.repository

import com.musicabinet.mobile.api.ApiFactory
import com.musicabinet.mobile.model.home.HomeData
import io.reactivex.Observable

/**
 * @author Kirchhoff-
 */
object DefaultMusicabinetRepository : MusicabinetRepository {

    private const val HOME_VIDEO_ID = "1eb9efe2-1428-476a-a69c-46c1cebb9dad"
    private const val HOME_NEWS_ID = "1eb9efe2-1428-476a-a69c-46c1cebb8dab"
    private const val HOME_TUTORIALS_ID = "1eb9efe2-1428-476a-a69c-46c1cebb9daa"
    private const val REQUEST_ITEM_COUNT = 5

    override fun getHomeNews(start: Int) =
            ApiFactory.service.getHomeItems(HOME_NEWS_ID, true, start, REQUEST_ITEM_COUNT)

    override fun getHomeTutorial(start: Int): Observable<HomeData> =
            ApiFactory.service.getHomeItems(HOME_TUTORIALS_ID, true, start, REQUEST_ITEM_COUNT)

    override fun getHomeVideo(start: Int): Observable<HomeData> =
            ApiFactory.service.getHomeItems(HOME_VIDEO_ID, true, start, REQUEST_ITEM_COUNT)
}