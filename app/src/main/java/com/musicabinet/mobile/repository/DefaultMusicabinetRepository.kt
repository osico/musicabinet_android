package com.musicabinet.mobile.repository

import com.musicabinet.mobile.api.ApiFactory
import com.musicabinet.mobile.model.lesson.progress.LessonProgress
import com.musicabinet.mobile.model.login.LoginRequestBody
import com.musicabinet.mobile.model.register.RegisterRequestBody
import com.musicabinet.mobile.model.register.UserInfo

/**
 * @author Kirchhoff-
 */
object DefaultMusicabinetRepository : MusicabinetRepository {

    private const val HOME_VIDEO_ID = "1eb9efe2-1428-476a-a69c-46c1cebb9dad"
    private const val HOME_NEWS_ID = "1eb9efe2-1428-476a-a69c-46c1cebb9dab"
    private const val HOME_TUTORIALS_ID = "1eb9efe2-1428-476a-a69c-46c1cebb9daa"
    private const val DEFAULT_SORT_FIELD = "el.sortOrder"
    private const val DEFAULT_SORT_DIR = "ASC"
    private const val REQUEST_ITEM_COUNT = 5

    private const val LOGIN_TYPE_EMAIL = "email"

    private const val DEFAULT_REQUEST_COUNT = 100

    private const val PREPARED_LESSON_STATUS = true

    private val LESSON_UPDATE_PROGRESS = LessonProgress(30000, false)

    override fun getHomeNews(start: Int) =
            ApiFactory.service.getHomeItems(HOME_NEWS_ID, true, start, REQUEST_ITEM_COUNT,
                    DEFAULT_SORT_FIELD, DEFAULT_SORT_DIR)

    override fun getHomeTutorial(start: Int) =
            ApiFactory.service.getHomeItems(HOME_TUTORIALS_ID, true, start, REQUEST_ITEM_COUNT,
                    DEFAULT_SORT_FIELD, DEFAULT_SORT_DIR)

    override fun getHomeVideo(start: Int) =
            ApiFactory.service.getHomeItems(HOME_VIDEO_ID, true, start, REQUEST_ITEM_COUNT,
                    DEFAULT_SORT_FIELD, DEFAULT_SORT_DIR)

    override fun getInstrumentList() =
            ApiFactory.service.getInstrumentList()

    override fun login(email: String, password: String) =
            ApiFactory.service.login(LoginRequestBody(LOGIN_TYPE_EMAIL, email, password))

    override fun getUserProfile() =
            ApiFactory.service.getUserProfile()

    override fun getInstrumentMatrix(instrumentId: String) =
            ApiFactory.service.getInstrumentMatrix(instrumentId)

    override fun getInstrumentMatrixFilter(instrumentId: String) =
            ApiFactory.service.getInstrumentMatrixFilter(instrumentId, true,
                    0, REQUEST_ITEM_COUNT)

    override fun registerUser(email: String, password: String, name: String, surname: String) =
            ApiFactory.service.registerUser(RegisterRequestBody(email, password, UserInfo(name, surname)))

    override fun getLessonGroup(id: String) =
            ApiFactory.service.getLessonGroup(id, DEFAULT_REQUEST_COUNT)

    override fun getNextLesson(id: String) =
            ApiFactory.service.getNextLesson(id, PREPARED_LESSON_STATUS)

    override fun getPreparedLesson(id: String) =
            ApiFactory.service.getPreparedLesson(id)

    override fun updateLessonProgress(id: String) =
            ApiFactory.service.updateLessonProgress(id, LESSON_UPDATE_PROGRESS)

    override fun downloadFile(fileId: String) = ApiFactory.service.downloadFile(fileId)

    override fun getTone() = ApiFactory.service.getTone()

    override fun getChordType() = ApiFactory.service.getChordType()

    override fun getNoteCourse(id: String) = ApiFactory.service.getNoteCourse(id)

    override fun getNoteModule(id: String) = ApiFactory.service.getNoteModule(id)

    override fun getNoteDiagram(moduleId: String, courseId: String, toneId: String,
                                chordTypeId: String)
            = ApiFactory.service.getNoteDiagram(moduleId, courseId, toneId, chordTypeId,
            true, true, 1, DEFAULT_REQUEST_COUNT,
            DEFAULT_SORT_FIELD, DEFAULT_SORT_DIR, true)

}