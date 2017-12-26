package com.musicabinet.mobile.ui.courses;

import com.musicabinet.mobile.model.instrument.matrix.InstrumentMatrixResponse;
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse;
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentGroup;
import com.musicabinet.mobile.model.instrument.matrix.remote.LessonCourse;
import com.musicabinet.mobile.model.instrument.matrix.remote.LessonGroup;
import com.musicabinet.mobile.repository.MusicabinetRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author Kirchhoff-
 */

public class CoursePresenter implements CoursesContract.Presenter {

    private final CoursesContract.View view;
    private final MusicabinetRepository repository;

    private CompositeDisposable disposable;

    CoursePresenter(CoursesContract.View view,
                    MusicabinetRepository repository) {
        this.view = view;
        this.repository = repository;

        disposable = new CompositeDisposable();
    }

    @Override
    public void loadInstrumentMatrix(@NotNull String instrumentId) {
        disposable.add(repository.getInstrumentMatrix(instrumentId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.showLoading(true);
                        view.showCourseError(false);
                        view.showCourseList(false);
                        view.showBuyButton(false);
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.showLoading(false);
                    }
                })
                .map(new Function<InstrumentMatrixResponse, List<InstrumentCourse>>() {
                    @Override
                    public List<InstrumentCourse> apply(InstrumentMatrixResponse response) throws Exception {

                        List<InstrumentCourse> instrumentCourseList = new ArrayList();

                        List<LessonCourse> lessonCourseList = response.getCourses();
                        for (int i = 0; i < lessonCourseList.size(); i++) {

                            List<LessonGroup> lessonGroupList = lessonCourseList.get(i).getLessonGroups();
                            List<InstrumentGroup> instrumentGroupList = new ArrayList<>();

                            for (int j = 0; j < lessonGroupList.size(); j++) {
                                InstrumentGroup instrumentGroup = new InstrumentGroup(lessonGroupList.get(j).getLessonList(),
                                        response.getModules().get(j));
                                instrumentGroupList.add(instrumentGroup);
                            }

                            instrumentCourseList.add(new InstrumentCourse(lessonCourseList.get(i).getName(),
                                    lessonCourseList.get(i).getProductPrice(),
                                    lessonCourseList.get(i).getId(),
                                    lessonCourseList.get(i).getProgress(),
                                    lessonCourseList.get(i).getProductActive(),
                                    lessonCourseList.get(i).getProductAvailable(),
                                    instrumentGroupList));

                        }

                        return instrumentCourseList;
                    }
                })
                .subscribe(new Consumer<List<InstrumentCourse>>() {
                               @Override
                               public void accept(List<InstrumentCourse> instrumentCourses) throws Exception {
                                   view.showCourseError(false);
                                   view.showCourseList(true);
                                   view.showCourses(instrumentCourses);
                                   view.showBuyButton(true);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.showCourseError(true);
                                view.showCourseList(false);
                            }
                        }));
    }

    @Override
    public void onBuyClick() {
        view.showPaymentDialog();
    }
}
