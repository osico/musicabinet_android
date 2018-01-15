package com.musicabinet.archmvp.base;

import android.arch.lifecycle.ViewModel;

/**
 * @author Kirchhoff-
 */

public final class BaseViewModel<V extends BaseContract.View, P extends BaseContract.Presenter<V>>
        extends ViewModel {

    private P presenter;

    public P getPresenter() {
        return this.presenter;
    }

    public void setPresenter(P presenter) {
        if (this.presenter == null)
            this.presenter = presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        presenter.onPresenterDestroy();
        presenter = null;
    }
}
