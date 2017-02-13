package com.bakkenbaeck.token.view.fragment.toplevel;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakkenbaeck.token.R;
import com.bakkenbaeck.token.databinding.FragmentAppsBinding;
import com.bakkenbaeck.token.presenter.AppsPresenter;
import com.bakkenbaeck.token.presenter.factory.AppsPresenterFactory;
import com.bakkenbaeck.token.presenter.factory.PresenterFactory;
import com.bakkenbaeck.token.view.fragment.BasePresenterFragment;

public class AppsFragment extends BasePresenterFragment<AppsPresenter, AppsFragment> {

    private FragmentAppsBinding binding;

    public static AppsFragment newInstance() {
        return new AppsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final @Nullable Bundle inState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_apps, container, false);
        return binding.getRoot();
    }

    public FragmentAppsBinding getBinding() {
        return this.binding;
    }

    @NonNull
    @Override
    protected PresenterFactory<AppsPresenter> getPresenterFactory() {
        return new AppsPresenterFactory();
    }

    @Override
    protected void onPresenterPrepared(@NonNull AppsPresenter presenter) {

    }

    @Override
    protected int loaderId() {
        return 2;
    }
}