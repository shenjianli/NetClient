package com.shen.netclient.engine;

/**
 * Created by shenjianli on 2016/7/14.
 */
public class Constants {
    /**
     * 手机代理IP
     */
    public static String PROXY;
    /**
     * 代理端口
     */
    public static int PORT;


    /**
     * 缓存大小常量
     */
    public static final int CACHE_SIZE = 1024 * 1024 * 50;  //NetClient中http请求缓存的最大值

    /**\
     * 表示网络请求的主url地址
     */
    public static String SERVER_BASE_URL = "http://m.shen.com.cn";
}
