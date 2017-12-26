package com.test.mvpretrofitrxjavademo.model;

import android.content.Context;

import com.test.mvpretrofitrxjavademo.entity.MovieInfo;
import com.test.mvpretrofitrxjavademo.netrequest.NetWorkHelper;
import com.test.mvpretrofitrxjavademo.netrequest.NetWorkRequestService;

import rx.Observable;

/**
 * @author:Victory
 * @time:2017/12/26
 * @Email:949021037@qq.com
 * @QQ:949021037
 * @explain; 电影数据获取类 再次之前先写网络请求
 */

public class MovieModel {
    NetWorkRequestService netWorkRequestService;
    //获取netWorkRequestService
    public MovieModel() {
        netWorkRequestService = NetWorkHelper.getInstance().getSersver();
    }
    //这里写的是固定返回5条数据
    public Observable<MovieInfo> getRequestMovieData() {
        return netWorkRequestService.getMovie(0,5);
    }
}
