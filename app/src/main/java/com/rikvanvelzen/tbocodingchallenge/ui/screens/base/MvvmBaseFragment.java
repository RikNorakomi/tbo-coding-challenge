/*
 * Created by Rik van Velzen, Norakomi Software Development.
 * Copyright (c) 2020. All rights reserved
 * Last modified 1/23/20 4:24 PM
 */

package com.rikvanvelzen.tbocodingchallenge.ui.screens.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import java.lang.reflect.ParameterizedType;
import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class MvvmBaseFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    public final String TAG = getClass().getSimpleName();

//    @Inject
//    ViewModelProvider.Factory viewModelFactory;

    protected B binding;
//    protected VM viewModel;

    /******************************************************
     * Abstract methods and optional overridable methods
     ******************************************************/

    @LayoutRes
    protected abstract int getLayoutResource();

    private ViewModelProvider getViewModelProvider() {
//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExchangeRateViewModel::class.java)

//        Log.e(TAG, "factory = " + viewModelFactory);
        return getActivity() != null ? ViewModelProviders.of(this) : null; // todo null case should never execute
//        return getActivity() != null ? ViewModelProviders.of(this, viewModelFactory) : null; // todo null case should never execute
    }

    /******************************************************
     * Fragment override methods
     ******************************************************/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false);
//        viewModel = getViewModelProvider().get(getViewModelClass());
//        binding.setVariable(com.rikvanvelzen.tbocodingchallenge.BR.viewModel, viewModel);
        binding.setLifecycleOwner(this);

        setupBaseViewModelObservers();

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    /******************************************************
     * Private methods
     ******************************************************/

    private void setupBaseViewModelObservers() {

//        viewModel.shouldNavigateBack().observe(getViewLifecycleOwner(), navigate -> getActivity().onBackPressed());
    }

    @SuppressWarnings("unchecked")
    private Class<VM> getViewModelClass() {

        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<VM>) parameterizedType.getActualTypeArguments()[1];
    }
}
