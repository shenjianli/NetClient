package com.shen.netclient.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.shen.netclient.NetClient;
import com.shen.netclient.test.api.PlatformApi;
import com.shen.netclient.test.bean.PlatformRes;
import com.shen.netclient.test.engine.HttpResultFunc;
import com.shen.netclient.util.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WebActivity extends AppCompatActivity {


    @Bind(R.id.web_load_btn)
    Button webLoadBtn;
    @Bind(R.id.web_btn_layout)
    LinearLayout webBtnLayout;
    @Bind(R.id.web_result)
    WebView webResult;
    @Bind(R.id.client_load_btn)
    Button clientLoadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        WebSettings webSettings = webResult.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setGeolocationDatabasePath(this.getDir("geodatabase", Context.MODE_PRIVATE).getPath());
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheMaxSize(8 * 1024 * 1024);
        webSettings.setAppCachePath(getDir("cache", Context.MODE_PRIVATE).getPath());
        webSettings.setDatabasePath(getDir("database", Context.MODE_PRIVATE).getPath());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.web_load_btn, R.id.web_result})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.web_load_btn:
                webResult.loadUrl("http://m.mall.shen.com.cn/mobile/touch/index.jhtml");
                break;
            case R.id.web_result:
                break;
        }
    }

    @OnClick(R.id.client_load_btn)
    public void onClick() {
        loadDataByRxAndroid1();
    }

    private Subscriber<PlatformRes> testSubscriber;
    public void loadDataByRxAndroid() {

        testSubscriber = new Subscriber<PlatformRes>() {
            @Override
            public void onCompleted() {
                LogUtils.i("表求完成");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.i(e.getStackTrace().toString());
            }

            @Override
            public void onNext(PlatformRes data) {
                LogUtils.i("获取数据成功");
                clientLoadBtn.setText(data.getData().getPlatform().get(0).getTitleName());

            }
        };
        PlatformApi testApi = NetClient.retrofit().create(PlatformApi.class);
        testApi.getPlatfromDataByRxAndroid().map(new HttpResultFunc<PlatformRes>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(testSubscriber);
    }


    public void loadDataByRxAndroid1() {

        testSubscriber = new Subscriber<PlatformRes>() {
            @Override
            public void onCompleted() {
                LogUtils.i("表求完成");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.i(e.getStackTrace().toString());
            }

            @Override
            public void onNext(PlatformRes data) {
                LogUtils.i("获取数据成功");
                clientLoadBtn.setText(data.getData().getPlatform().get(0).getTitleName());

            }
        };
        PlatformApi testApi = NetClient.retrofit().create(PlatformApi.class);
        testApi.getPlatfromDataByRxAndroid1()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(testSubscriber);
    }
}
