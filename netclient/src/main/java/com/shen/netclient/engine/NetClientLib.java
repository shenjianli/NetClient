package com.shen.netclient.engine;


import android.content.Context;

import com.shen.netclient.util.LogUtils;
/**
 * Created by shenjianli on 2016/7/7.
 */
public class NetClientLib{

    private static NetClientLib mLibApp;

    private Context mMobileContext;

    private String serverBaseUrl = "";

    public Context getMobileContext() {
        return mMobileContext;
    }

    public void setMobileContext(Context mMobileContext) {
        this.mMobileContext = mMobileContext;
        //Stetho.initializeWithDefaults(mMobileContext);
    }

    // 私有化构造函数
    private NetClientLib(){

    }

    // 懒汉模式获取单实例
    public synchronized static NetClientLib getLibInstance(){
       if(null == mLibApp){
           mLibApp = new NetClientLib();
       }
        return mLibApp;
    }

    public void setLogEnable(boolean enable){
        LogUtils.isOutPutLog = enable;
    }

    public void setUrlConfigManager(int configFileId){
        if(-1 == configFileId){
           UrlConfigManager.MockServiceEnable  = false;
        }
        else{
            UrlConfigManager.getUrlConfigManager().initUrlConfigManager(configFileId);
        }
    }

    public void setBeanFactoryConfig(int fileId){
        if( -1 != fileId){
            BeanFactory.getBeanFactory().initBeanFactory(fileId);
        }
        else{
            LogUtils.w("BeanFactory 配置文件为空");
        }
    }

    public void setServerBaseUrl(String url) {
        this.serverBaseUrl = url;
    }

    public String getServerBaseUrl() {
        return serverBaseUrl;
    }
}
