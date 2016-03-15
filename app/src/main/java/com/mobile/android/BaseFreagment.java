package com.mobile.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xinming.xxm on 2015/12/2.
 */
public abstract class BaseFreagment extends Fragment{

    protected View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null == mRootView){
            mRootView = initRootView(inflater);
            initView();
        }
        return mRootView;
    }

    protected abstract View initRootView(LayoutInflater inflater);

    protected abstract void initView();
}
