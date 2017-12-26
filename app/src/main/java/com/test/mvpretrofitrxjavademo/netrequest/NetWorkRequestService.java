package com.test.mvpretrofitrxjavademo.netrequest;

import com.test.mvpretrofitrxjavademo.entity.MovieInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author:Victory
 * @time:2017/12/26
 * @Email:949021037@qq.com
 * @QQ:949021037
 * @explain;
 */

public interface NetWorkRequestService {
    @GET("movie/top250")
    Observable<MovieInfo> getMovie(@Query("start") int start, @Query("count") int count);
}
