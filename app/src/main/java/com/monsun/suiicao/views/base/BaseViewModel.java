package com.monsun.suiicao.views.base;

import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

public abstract class BaseViewModel<T> extends ViewModel {

    private WeakReference<T> mNavigator;

    public BaseViewModel ()
    {

    }
    public void setNavigator(T navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public T getNavigator() {
        return mNavigator.get();
    }

}
