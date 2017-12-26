package com.test.mvpretrofitrxjavademo.view;

/**
 * @author:Victory
 * @time:2017/12/26
 * @Email:949021037@qq.com
 * @QQ:949021037
 * @explain; 父接口，用于数据请求成功后回调
 */

public interface BaseView<T> {
    /**
     * 请求中.....
     */
    void requestIng(String string);

    /**
     * 请求成功
     *
     * @param data
     */
    void requestSuccess(T data);

    /**
     * 请求失败（非网络问题，一般为后台返回失败信息）
     *
     * @param errorMessage
     */
    void requestError(String errorMessage);
}
