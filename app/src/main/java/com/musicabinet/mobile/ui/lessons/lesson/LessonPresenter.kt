package com.musicabinet.mobile.ui.lessons.lesson

import android.app.Activity
import android.content.Intent
import com.musicabinet.mobile.model.lesson.lesson.Lesson
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
                .subscribe({ view.showSuccess() }, { view.showError() }))
    }


    override fun selectLessonClick() {
        if (lessonList != null)
            view.showSelectLesson(lessonList!!, REQUEST_LESSON_CODE,
                    REQUEST_LESSON_ID_RESULT, REQUEST_LESSON_NAME_RESULT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_LESSON_CODE && resultCode == Activity.RESULT_OK) {
            view.onLessonSelected()
        }
    }

}