package com.musicabinet.archmvp;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.musicabinet.archmvp.base.BaseContract;
import com.musicabinet.archmvp.base.BaseViewModel;


public abstract class BaseFragment<V extends BaseContract.View, P extends BaseContract.Presenter<V>>
        extends Fragment implements BaseContract.View {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    protected P presenter;

    @SuppressWarnings("unchecked")
    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BaseViewModel<V, P> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        boolean isPresenterCreated = false;
        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(initPresenter());
            isPresenterCreated = true;
        }
        presenter = viewModel.getPresenter();
        presenter.attachLifecycle(getLifecycle());
        presenter.attachView((V) this);
        if (isPresenterCreated)
            presenter.onPresenterCreated();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachLifecycle(getLifecycle());
        presenter.detachView();
    }

    protected abstract P initPresenter();
}