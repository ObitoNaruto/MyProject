package com.mobile.android.home.view;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by xinming.xxm on 2015/12/2.
 */
public interface IHomeView {
    /**
     * 更新ui
     * @param datas
     */
    void onUpdateView(List<Fragment> datas);

    /**
     * 弹出Toast
     * @param msg
     */
    void toast(String msg);
}
