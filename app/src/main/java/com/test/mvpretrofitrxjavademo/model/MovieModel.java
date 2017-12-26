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

    public MovieModel(Context context) {
        netWorkRequestService = NetWorkHelper.getInstance(context).getSersver();
    }

    public Observable<MovieInfo> getRequestMovieData() {
        return netWorkRequestService.getMovie(0,5);
    }
}
