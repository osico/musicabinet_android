package com.musicabinet.mobile.ui.lessons.lesson

import com.musicabinet.mobile.model.lesson.Lesson
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

    override fun selectLessonClick() {
        if (lessonList != null)
            view.showLessonsDialog(lessonList!!)
    }

}