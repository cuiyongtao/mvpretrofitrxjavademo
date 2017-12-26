package com.test.mvpretrofitrxjavademo.netrequest;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author:Victory
 * @time:2017/12/26
 * @Email:949021037@qq.com
 * @QQ:949021037
 * @explain; 网络请求Retrofit设置
 */

public class NetWorkHelper {

    private Retrofit mRetrofit;

    private String BASEURL = "https://api.douban.com/v2/";

    private static NetWorkHelper netWorkHelperInstance = null;
    //实例用于Model调用
    public static NetWorkHelper getInstance() {
        if (netWorkHelperInstance == null) {
            netWorkHelperInstance = new NetWorkHelper();
        }
        return netWorkHelperInstance;
    }
    //构造器
    public NetWorkHelper() {
        initRetrofit();
    }

    //Retrofit设置
    private Retrofit initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit;
    }

    //将方法抽离，避免代码冗余
    public NetWorkRequestService getSersver() {
        return mRetrofit.create(NetWorkRequestService.class);
    }
}
