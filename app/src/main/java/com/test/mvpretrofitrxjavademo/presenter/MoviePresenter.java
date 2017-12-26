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
    CompositeSubscription mCompositeSubscription;
    Context mContext;

    public MoviePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreat() {
        mMovieInfo = new MovieInfo();
        movieModel = new MovieModel(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart(BaseView view) {
        movieView = (MovieView) view;
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void setRequestData() {
        movieView.requestIng("请求中");
        mCompositeSubscription.add(movieModel.getRequestMovieData()
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieInfo>() {
                    @Override
                    public void onCompleted() {
                        if (mMovieInfo != null) {
                            movieView.requestSuccess(mMovieInfo);
                        } else {
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

