package com.shen.netclient.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_test)
    Button mainTest;
    @Bind(R.id.main_cookie)
    Button mainCookie;
    @Bind(R.id.main_web)
    Button mainWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void click(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.setClass(this, CookieActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.main_test, R.id.main_cookie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_test:
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
            case R.id.main_cookie:
                Intent intent1 = new Intent();
                intent1.setClass(this, CookieActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @OnClick(R.id.main_web)
    public void onClick() {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }
}
