package com.musicabinet.archmvp.base;

public interface BaseLoadingContract {

    interface View extends BaseContract.View {

        void showLoading();

        void hideLoading();

        void showError(String errorMessage);
    }

    interface Presenter extends BaseContract.Presenter<BaseContract.View> {

    }
}
