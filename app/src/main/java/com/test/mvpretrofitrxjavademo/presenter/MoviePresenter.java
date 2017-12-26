package com.test.mvpretrofitrxjavademo.presenter;

import android.content.Context;

import com.test.mvpretrofitrxjavademo.entity.MovieInfo;
import com.test.mvpretrofitrxjavademo.model.MovieModel;
import com.test.mvpretrofitrxjavademo.view.BaseView;
import com.test.mvpretrofitrxjavademo.view.MovieView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author:Victory
 * @time:2017/12/26
 * @Email:949021037@qq.com
 * @QQ:949021037
 * @explain; model和view的连接器
 */

public class MoviePresenter implements Presenter {

    MovieModel movieModel;
    MovieView movieView;
    MovieInfo mMovieInfo;
    //用于解绑
    CompositeSubscription mCompositeSubscription;
    Context mContext;

    public MoviePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreat() {
        //初始化
        mMovieInfo = new MovieInfo();
        movieModel = new MovieModel();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart(BaseView view) {
        //初始化View
        movieView = (MovieView) view;
    }

    @Override
    public void onStop() {
        //解绑
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void setRequestData() {
        //数据请求中
        movieView.requestIng("请求中");
        mCompositeSubscription.add(movieModel.getRequestMovieData()
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieInfo>() {
                    @Override
                    public void onCompleted() {
                        //因为没找到code值所以就直接写了
                        if (mMovieInfo != null) {
                            //数据正确时，返回数据如code等于200
                            movieView.requestSuccess(mMovieInfo);
                        } else {
                            //error包括很多种，如code值非正确时返回的错误信息
                            movieView.requestError("数据有误");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieView.requestError(e.toString());
                    }

                    @Override
                    public void onNext(MovieInfo movieInfo) {
                        mMovieInfo = movieInfo;
                    }
                }));
    }

}

