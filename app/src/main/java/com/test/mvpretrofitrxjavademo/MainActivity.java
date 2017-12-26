package com.test.mvpretrofitrxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.test.mvpretrofitrxjavademo.entity.MovieInfo;
import com.test.mvpretrofitrxjavademo.presenter.MoviePresenter;
import com.test.mvpretrofitrxjavademo.view.BaseView;
import com.test.mvpretrofitrxjavademo.view.MovieView;

public class MainActivity extends AppCompatActivity implements MovieView {
    private TextView mbtn;
    MoviePresenter moviePresenter;
    MovieInfo movieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        movieInfo = new MovieInfo();
        moviePresenter = new MoviePresenter(this);
        mbtn = findViewById(R.id.btn);
        moviePresenter.onCreat();
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviePresenter.setRequestData();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        moviePresenter.onStart(this);
    }

    @Override
    public void requestSuccess(Object data) {
        movieInfo = (MovieInfo) data;
        mbtn.setText(movieInfo.getTitle());
    }

    @Override
    public void requestIng(String string) {
        mbtn.setText(string);
    }

    @Override
    public void requestError(String errorMessage) {
        mbtn.setText(errorMessage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moviePresenter.onStop();
    }
}
