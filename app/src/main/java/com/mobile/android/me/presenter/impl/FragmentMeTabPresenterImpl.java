package com.mobile.android.me.presenter.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.mobile.android.R;
import com.mobile.android.me.event.FragmentMeTabGetDatasEvent;
import com.mobile.android.me.model.BaseItem;
import com.mobile.android.me.model.CommonItem;
import com.mobile.android.me.model.LifeItem;
import com.mobile.android.me.model.LiveItem;
import com.mobile.android.me.model.MedicalItem;
import com.mobile.android.me.model.TrafficItem;
import com.mobile.android.me.presenter.IFragmentMeTabPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by xinming.xxm on 2015/12/4.
 */
public class FragmentMeTabPresenterImpl implements IFragmentMeTabPresenter{

    private Context mContext;

    public FragmentMeTabPresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void loadDatas() {
        String[] meTabs = mContext.getResources().getStringArray(R.array.me_tab_lists);
        final List<String> datas = new ArrayList<>();
        Collections.addAll(datas, meTabs);

        final List<BaseItem> items = new ArrayList<>();
        TrafficItem trafficItem = new TrafficItem();
        trafficItem.setName(datas.get(0));
        items.add(trafficItem);

//        LifeItem lifeItem = new LifeItem();
//        lifeItem.setName(datas.get(1));
//        items.add(lifeItem);
//
//        MedicalItem medicalItem = new MedicalItem();
//        medicalItem.setName(datas.get(2));
//        items.add(medicalItem);
//
//        LiveItem liveItem = new LiveItem();
//        liveItem.setName(datas.get(3));
//        items.add(liveItem);
//
//        CommonItem commonItem = new CommonItem();
//        commonItem.setName(datas.get(4));
//        items.add(commonItem);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentMeTabGetDatasEvent event = new FragmentMeTabGetDatasEvent(items);
                EventBus.getDefault().post(event);
            }
        }, 1000);
    }
}
