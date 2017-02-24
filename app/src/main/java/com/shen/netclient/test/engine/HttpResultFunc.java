package com.shen.netclient.test.engine;

import com.shen.netclient.test.bean.BaseData;
import com.shen.netclient.util.LogUtils;

import rx.functions.Func1;

/**
 * Created by shenjianli on 2016/8/15.
 */
public class HttpResultFunc<T> implements Func1<BaseData<T>,T>{

    @Override
    public T call(BaseData<T> httpResult) {
        if (httpResult.getReqCode() == BaseData.REQ_EXCE) {
            throw new ApiException(httpResult.getMsg());
        }
        else if (httpResult.getReqCode() == BaseData.REQ_FAIL) {
           LogUtils.i("请求失败" + httpResult.getMsg());
        }
        return httpResult.getData();
    }

}
