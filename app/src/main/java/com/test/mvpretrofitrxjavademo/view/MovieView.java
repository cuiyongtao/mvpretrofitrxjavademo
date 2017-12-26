package com.test.mvpretrofitrxjavademo.view;

/**
 * @author:Victory
 * @time:2017/12/26
 * @Email:949021037@qq.com
 * @QQ:949021037
 * @explain; 电影视图
 */

public interface MovieView extends BaseView {
    @Override
    void requestSuccess(Object data);

    @Override
    void requestIng(String string);

    @Override
    void requestError(String errorMessage);
}
