package com.shen.netclient.test;

import android.app.Application;

import com.shen.netclient.engine.NetClientLib;
import com.shen.netclient.util.FileUtils;
import com.shen.netclient.util.LogUtils;

/**
 * Created by edianzu on 2017/2/24.
 */
public class App extends Application{
    private static App mMobileApp;
    @Override
    public void onCreate() {
        super.onCreate();
        NetClientLib.getLibInstance().setMobileContext(this);
        initByGradleFile();
        NetClientLib.getLibInstance().setBeanFactoryConfig(R.raw.bean);
        mMobileApp = this;
    }


    /*
    根据主项目中的gradle配置文件开初始化不同的开发模式
     */
    private void initByGradleFile() {

        if(Constants.TEST_MODE.equals(BuildConfig.MODE)){
            NetClientLib.getLibInstance().setLogEnable(true);
            NetClientLib.getLibInstance().setUrlConfigManager(R.xml.url);
        }
        else if(Constants.DEV_MODE.equals(BuildConfig.MODE))
        {
            NetClientLib.getLibInstance().setLogEnable(true);
            NetClientLib.getLibInstance().setServerBaseUrl(BuildConfig.SERVER_URL);
        }
        else if(Constants.RELEASE_MODE.equals(BuildConfig.MODE)){
            NetClientLib.getLibInstance().setLogEnable(true);
            NetClientLib.getLibInstance().setServerBaseUrl(BuildConfig.SERVER_URL);
        }
    }


    /*
    根据Raw中的mobile配置文件来初始化开发模式
     */
    private void initByRawConfigFile() {
        if(FileUtils.getProperties(this, R.raw.mode)){
            String mode = FileUtils.getPropertyValueByKey("mode");
            String baseUrl = FileUtils.getPropertyValueByKey("baseUrl");
            LogUtils.i("开发模式为：" + mode);
            if(Constants.TEST_MODE.equals(mode)){
                NetClientLib.getLibInstance().setLogEnable(true);
                NetClientLib.getLibInstance().setUrlConfigManager(R.xml.url);
            }
            else if(Constants.DEV_MODE.equals(BuildConfig.MODE))
            {
                NetClientLib.getLibInstance().setLogEnable(true);
                NetClientLib.getLibInstance().setServerBaseUrl(baseUrl);
            }
            else if(Constants.RELEASE_MODE.equals(BuildConfig.MODE)){
                NetClientLib.getLibInstance().setLogEnable(false);
                NetClientLib.getLibInstance().setServerBaseUrl(baseUrl);
            }
        }
    }

    public static App getAppInstance(){
        return  mMobileApp;
    }

}
