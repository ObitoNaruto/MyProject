package com.mobile.android.home.presenter.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.mobile.android.chat.ChatMainTabFragment;
import com.mobile.android.contact.ContactTabFragment;
import com.mobile.android.discovery.DiscoveryTabFragment;
import com.mobile.android.home.presenter.IHomePresenter;
import com.mobile.android.home.util.FragmentHolder;
import com.mobile.android.home.view.IHomeView;
import com.mobile.android.me.MeTabFragment;

/**
 * Created by xinming.xxm on 2015/12/2.
 */
public class HomePresenterImpl implements IHomePresenter{
    public static FragmentHolder mFragmentHolder;
    static{
        mFragmentHolder = new FragmentHolder();
        mFragmentHolder.addFragment("冀秦", new ChatMainTabFragment());
        mFragmentHolder.addFragment("通信录", new ContactTabFragment());
        mFragmentHolder.addFragment("发现", new DiscoveryTabFragment());
        mFragmentHolder.addFragment("我的", new MeTabFragment());
    }
    private Context mContext;
    private IHomeView mHomeView;

    public HomePresenterImpl(Context mContext, IHomeView homeView) {
        this.mContext = mContext;
        this.mHomeView = homeView;
    }

    @Override
    public void loadDatas() {
//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
                mHomeView.onUpdateView(mFragmentHolder.getFragmenList());
//            }
//        }, 500);
    }
}
