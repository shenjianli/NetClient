package com.shen.netclient;

import android.text.TextUtils;

import com.shen.netclient.engine.Constants;
import com.shen.netclient.engine.NetClientLib;
import com.shen.netclient.net.cookie.PersistentCookieStore;
import com.shen.netclient.net.cookie.WebkitCookieManagerProxy;
import com.shen.netclient.net.interceptor.CacheInterceptor;
import com.shen.netclient.net.interceptor.HeaderInterceptor;
import com.shen.netclient.net.interceptor.MockServerInterceptor;
import com.shen.netclient.net.interceptor.QueryParameterInterceptor;
import com.shen.netclient.util.LogUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class NetClient {

	public static Retrofit retrofit = null;
    public static Retrofit retrofit() {
        if (retrofit == null) {
	         OkHttpClient.Builder builder = new OkHttpClient.Builder();
            /**
             *设置缓存
             */
	         File cacheFile = new File(NetClientLib.getLibInstance().getMobileContext().getExternalCacheDir(), "MallCache");
	         Cache cache = new Cache(cacheFile, Constants.CACHE_SIZE);
            CacheInterceptor noNetcache = new CacheInterceptor();//无网络拦截器
            builder.cache(cache).addInterceptor(new CacheInterceptor());
            builder.interceptors().add(noNetcache);//无网络
            builder.networkInterceptors().add(noNetcache);//有网络
            //builder.networkInterceptors().add(new StethoInterceptor());//有网络

            /**
             *  公共参数，代码略
             */
             
            //公共参数
            builder.addInterceptor(new QueryParameterInterceptor());
            /**
             * 设置头，代码略
             */           
            //设置头
            builder.addInterceptor(new HeaderInterceptor());
			 /**
             * Log信息拦截器，代码略
             */
             if (LogUtils.isOutPutLog) {
            	    // Log信息拦截器
            	    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            	    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            	    //设置 Debug Log 模式
            	    builder.addInterceptor(loggingInterceptor);
            	}
			 /**
             * 设置cookie，代码略
             */
             //CookieManager cookieManager = new CookieManager();
             //cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
             //builder.cookieJar(new JavaNetCookieJar(cookieManager));
             
             PersistentCookieStore persistentCookieStore = new PersistentCookieStore(NetClientLib.getLibInstance().getMobileContext());
             WebkitCookieManagerProxy coreCookieManager = new WebkitCookieManagerProxy(persistentCookieStore, java.net.CookiePolicy.ACCEPT_ALL);
             //java.net.CookieHandler.setDefault(coreCookieManager);
             builder.cookieJar(new JavaNetCookieJar(coreCookieManager));

            /**
             * 设置MockService
             */
            builder.addInterceptor(new MockServerInterceptor());

            /**
            * 设置超时和重连，代码略
            */
           //设置超时
             builder.connectTimeout(15, TimeUnit.SECONDS);
             builder.readTimeout(20, TimeUnit.SECONDS);
             builder.writeTimeout(20, TimeUnit.SECONDS);
             //错误重连
             builder.retryOnConnectionFailure(true);

            //以上设置结束，才能build(),不然设置白搭
            OkHttpClient okHttpClient = builder.build();
            String url = NetClientLib.getLibInstance().getServerBaseUrl();
            if(TextUtils.isEmpty(url)){
                url = Constants.SERVER_BASE_URL;
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(okHttpClient)
                    //增加返回值为String的支持
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //增加返回值为Gson的支持(以实体类返回)
                    .addConverterFactory(GsonConverterFactory.create())
                    //增加返回值为Oservable<T>的支持
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
