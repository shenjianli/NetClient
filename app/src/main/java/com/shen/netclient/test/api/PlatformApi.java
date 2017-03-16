package com.shen.netclient.test.api;

import com.shen.netclient.test.bean.BaseData;
import com.shen.netclient.test.bean.PlatformRes;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by edianzu on 2017/3/16.
 */
public interface PlatformApi {

    //请求的url地址
    @GET("mobile/indexPlatformNew.jhtml?flag=1")
    //id为请求的参数，可以根据参数不同返回不同的返回结果
    Observable<BaseData<PlatformRes>> getPlatfromDataByRxAndroid();

}
