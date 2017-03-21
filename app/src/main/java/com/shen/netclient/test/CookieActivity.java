package com.shen.netclient.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shen.netclient.NetClient;
import com.shen.netclient.engine.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Cookie;

public class CookieActivity extends AppCompatActivity {

    @Bind(R.id.cookie_result)
    TextView cookieResult;
    @Bind(R.id.save_cookie_btn)
    Button saveCookieBtn;
    @Bind(R.id.get_cookie_btn)
    Button getCookieBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.save_cookie_btn, R.id.get_cookie_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_cookie_btn:
                List<Cookie> cookies = new ArrayList<>();
                Cookie cooke = new Cookie.Builder().name("shen").value("jianli").domain("m.mall.icbc.com.cn").path("/").build();
                Cookie cooke1 = new Cookie.Builder().name("type").value("android").domain("m.mall.icbc.com.cn").path("/").build();
                cookies.add(cooke);
                cookies.add(cooke1);
                NetClient.addCookie(Constants.SERVER_BASE_URL,cookies);
                break;
            case R.id.get_cookie_btn:
                List<Cookie> cookie = NetClient.getCookie(Constants.SERVER_BASE_URL);
                StringBuilder cookieStr = new StringBuilder();
                if(null != cookie){
                    for (Cookie cookie1 : cookie) {
                        cookieStr.append(cookie1.name() + "=" + cookie1.value() + ",domain:" + cookie1.domain() + ",path:" + cookie1.path() + "       ");
                    }
                }
                cookieResult.setText(cookieStr.toString());
                break;
        }
    }
}
