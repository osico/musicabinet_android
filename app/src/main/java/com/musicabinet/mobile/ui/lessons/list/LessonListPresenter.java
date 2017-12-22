package com.musicabinet.mobile.ui.lessons.list;

import com.musicabinet.mobile.model.instrument.matrix.LessonItem;
import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterResponse;
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse;
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentGroup;
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentLessonList;
import com.musicabinet.mobile.repository.MusicabinetRepository;
import com.musicabinet.mobile.repository.keyvalue.MusicabinetKeyValueStorage;

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

public class LessonListPresenter implements LessonListContract.Presenter {

    private final LessonListContract.View view;
    private final MusicabinetRepository repository;
    private final MusicabinetKeyValueStorage storage;
    private final InstrumentCourse instrumentCourse;
    private CompositeDisposable disposable;

    LessonListPresenter(LessonListContract.View view,
                        MusicabinetRepository repository,
                        MusicabinetKeyValueStorage storage,
                        InstrumentCourse instrumentCourse) {
        this.view = view;
        this.repository = repository;
        this.storage = storage;
        this.instrumentCourse = instrumentCourse;
        disposable = new CompositeDisposable();
    }

    @Override
    public void getFilters(@NotNull String instrumentId) {
        disposable.add(repository.getInstrumentMatrixFilter(instrumentId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.showLoading(true);
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.showLoading(false);
                    }
                }).map(new Function<InstrumentFilterResponse, List<InstrumentLessonList>>() {
                    @Override
                    public List<InstrumentLessonList> apply(InstrumentFilterResponse response) throws Exception {
                        if (!response.getFilterList().isEmpty()) {
                            List<InstrumentLessonList> resultList = new ArrayList<>();

                            for (int i = 0; i < response.getFilterList().size(); i++) {

                                List<InstrumentGroup> filteredInstrumentGroup = new ArrayList<>();

                                for (int j = 0; j < instrumentCourse.getLessonGroups().size(); j++) {
                                    InstrumentGroup instrumentGroup = instrumentCourse.getLessonGroups().get(j);
                                    if (instrumentGroup.isContainsFilter(response.getFilterList().get(i).getId()))
                                        filteredInstrumentGroup.add(instrumentGroup);
                                }

                                resultList.add(new InstrumentLessonList(response.getFilterList().get(i).getName(),
                                        filteredInstrumentGroup));
                            }

                            return resultList;

                        } else {

                        }
                        return null;
                    }
                })
                .subscribe(new Consumer<List<InstrumentLessonList>>() {
                    @Override
                    public void accept(List<InstrumentLessonList> instrumentFilterResponse) throws Exception {
                        view.showLessonFilter(instrumentFilterResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();
                    }
                }));
    }

    @Override
    public void buyLesson(@NotNull String lessonId) {
        view.showPaymentDialog();
    }

    @Override
    public void onLessonClick(@NotNull LessonItem item) {
        if (storage.isUserExist())
            view.moveToLesson(item.getId());
        else
            view.showAuthorizedError();
    }
}
