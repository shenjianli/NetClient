package com.shen.netclient.test.engine;

import com.shen.netclient.test.bean.Test;

public interface DataView extends MvpView {

    void loadData(Test data);
}
