package com.test.mvpretrofitrxjavademo.presenter;

import com.test.mvpretrofitrxjavademo.view.BaseView;

/**
 * @author:Victory
 * @time:2017/12/26
 * @Email:949021037@qq.com
 * @QQ:949021037
 * @explain; 主Presenter
 * 最好和
 */

public interface Presenter {

    void onCreat();

    void onStart(BaseView view);

    void onStop();
}
