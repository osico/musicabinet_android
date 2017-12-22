package com.musicabinet.mobile.ui.lessons.lesson

import android.app.Activity
import android.content.Intent
import com.musicabinet.mobile.model.lesson.lesson.Lesson
import com.musicabinet.mobile.model.lesson.local.LessonData
import com.musicabinet.mobile.model.lesson.local.LessonScreenData
import com.musicabinet.mobile.model.lesson.local.MethodItem
import com.musicabinet.mobile.model.lesson.remote.LessonResponse
import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Kirchhoff-
 */
class LessonPresenter(private val view: LessonContract.View,
                      private val repository: MusicabinetRepository) : LessonContract.Presenter {

    companion object {
        private const val REQUEST_LESSON_CODE = 10020
        private const val REQUEST_LESSON_NAME_RESULT = "REQUEST_LESSON_NAME_RESULT"
        private const val REQUEST_LESSON_ID_RESULT = "REQUEST_LESSON_ID_RESULT"
    }

    private val subscriptions = CompositeDisposable()
    private var lessonList: List<Lesson>? = null
    private lateinit var currentLessonId: String

    override fun getLessonGroup(id: String) {
        subscriptions.add(repository.getLessonGroup(id)
                .observeOn(AndroidSchedulers.mainThread())
                .map { lessonGroup ->
                    Collections.sort(lessonGroup.lessonList)
                    lessonGroup.lessonList
                }
                .subscribe({ list: List<Lesson> ->
                    lessonList = ArrayList<Lesson>(list)
                    view.showSuccess()
                }, { view.showError() }))
    }

    override fun getLessonInformation(id: String) {
        subscriptions.add(repository.getNextLesson(id)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doOnTerminate { view.showLoading(false) }
                .map({ lessonResponse: LessonResponse ->
                    val methodList = ArrayList<MethodItem>()
                    val lessonImageList = ArrayList<LessonData>()
                    for (lessonPart in lessonResponse.lessonParts) {
                        if (lessonPart.video != null && lessonPart.video!!.video != null)
                            methodList.add(MethodItem(lessonPart.video!!.description,
                                    lessonPart.description, lessonPart.video!!.video!!))
                    }

                    for (i in lessonResponse.lessonParts.indices) {
                        val images = ArrayList<String>()
                        for (exercise in lessonResponse.lessonParts[i].exercisesList)
                            images.add(exercise.stave.file.id)

                        lessonImageList.add(LessonData(lessonResponse.lessonParts[i].name, images))
                    }

                    currentLessonId = lessonResponse.id
                    LessonScreenData(lessonResponse.name, methodList, lessonImageList)
                })
                .subscribe({ screenData: LessonScreenData ->
                    view.showSuccess()
                    view.showMethod(screenData.methodList)
                    view.showLessonTitle(screenData.title)
                    view.showLessonImages(screenData.lessonImages)
                }, { view.showError() }))
    }

    override fun getPreparedLesson(id: String) {
        subscriptions.add(repository.getPreparedLesson(id)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doOnTerminate { view.showLoading(false) }
                .map({ lessonResponse: LessonResponse ->
                    val methodList = ArrayList<MethodItem>()
                    val lessonImageList = ArrayList<LessonData>()
                    for (lessonPart in lessonResponse.lessonParts) {
                        if (lessonPart.video != null && lessonPart.video!!.video != null)
                            methodList.add(MethodItem(lessonPart.video!!.description,
                                    lessonPart.description, lessonPart.video!!.video!!))
                    }

                    for (i in lessonResponse.lessonParts.indices) {
                        val images = ArrayList<String>()
                        for (exercise in lessonResponse.lessonParts[i].exercisesList)
                            images.add(exercise.stave.file.id)

                        lessonImageList.add(LessonData(lessonResponse.lessonParts[i].name, images))
                    }

                    currentLessonId = lessonResponse.id
                    LessonScreenData(lessonResponse.name, methodList, lessonImageList)
                })
                .subscribe({ screenData: LessonScreenData ->
                    view.showSuccess()
                    view.showMethod(screenData.methodList)
                    view.showLessonTitle(screenData.title)
                    view.showLessonImages(screenData.lessonImages)
                }, { view.showError() }))
    }


    override fun selectLessonClick() {
        if (lessonList != null)
            view.showSelectLesson(lessonList!!, currentLessonId, REQUEST_LESSON_CODE,
                    REQUEST_LESSON_ID_RESULT, REQUEST_LESSON_NAME_RESULT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_LESSON_CODE && resultCode == Activity.RESULT_OK &&
                data != null) {
            view.onLessonSelected(data.getStringExtra(REQUEST_LESSON_ID_RESULT))
        }
    }

}