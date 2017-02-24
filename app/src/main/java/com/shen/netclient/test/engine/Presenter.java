package com.shen.netclient.test.engine;

import com.shen.netclient.test.engine.MvpView;

/**
 * Created by toryang on 16/4/27.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

}
