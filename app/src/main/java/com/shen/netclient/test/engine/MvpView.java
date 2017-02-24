package com.shen.netclient.test.engine;

public interface MvpView {
    void startLoading();
    void hideLoading();
    void showError(String msg);
}