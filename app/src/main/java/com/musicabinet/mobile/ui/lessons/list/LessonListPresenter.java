package com.musicabinet.mobile.ui.lessons.list;

import android.app.Activity;
import android.content.Intent;

import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInResult;
import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterResponse;
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse;
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentGroup;
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentLessonList;
import com.musicabinet.mobile.model.order.OrderIdResponse;
import com.musicabinet.mobile.model.order.execute.OrderExecuteResponse;
import com.musicabinet.mobile.model.order.finish.OrderFinishExecuteResponse;
import com.musicabinet.mobile.repository.MusicabinetRepository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    private final static int REQUEST_PAYMENT_CODE = 1003;

    private final LessonListContract.View view;
    private final MusicabinetRepository repository;
    private final InstrumentCourse instrumentCourse;
    private CompositeDisposable disposable;

    private String orderId;

    LessonListPresenter(LessonListContract.View view,
                        MusicabinetRepository repository,
                        InstrumentCourse instrumentCourse) {
        this.view = view;
        this.repository = repository;
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
        disposable.add(repository.createOrder(lessonId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.showBuyLoading(true);
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.showBuyLoading(false);
                    }
                }).subscribe(new Consumer<OrderIdResponse>() {
                    @Override
                    public void accept(OrderIdResponse orderIdResponse) throws Exception {
                        orderId = orderIdResponse.getId();
                        executePayment(orderIdResponse.getId());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();
                    }
                }));
    }


    private void executePayment(String id) {
        disposable.add(repository.executeOrder(id)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.showBuyLoading(true);
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.showBuyLoading(false);
                    }
                })
                .subscribe(new Consumer<OrderExecuteResponse>() {
                    @Override
                    public void accept(OrderExecuteResponse orderExecuteResponse) throws Exception {
                        view.moveToPaymentScreen(orderExecuteResponse.getTokenItem().getToken(),
                                REQUEST_PAYMENT_CODE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();
                    }
                }));
    }

    private void executeFinishPayment(String nonce) {
        disposable.add(repository.finishExecuteOrder(orderId, nonce)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.showBuyLoading(true);
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.showBuyLoading(false);
                    }
                })
                .subscribe(new Consumer<OrderFinishExecuteResponse>() {
                    @Override
                    public void accept(OrderFinishExecuteResponse orderFinishExecuteResponse) throws Exception {
                        view.showSuccessPayment();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();
                    }
                }));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_PAYMENT_CODE) {

            if (resultCode == Activity.RESULT_OK && data != null) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                if (result.getPaymentMethodNonce() != null &&
                        result.getPaymentMethodNonce().getNonce() != null)
                    executeFinishPayment(result.getPaymentMethodNonce().getNonce());
                else
                    view.showError();
            } else if (resultCode != Activity.RESULT_CANCELED) {
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                view.showError();
            }
        }
    }
}
