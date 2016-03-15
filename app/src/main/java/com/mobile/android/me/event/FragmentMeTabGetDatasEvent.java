package com.mobile.android.me.event;

import com.mobile.android.me.model.BaseItem;

import java.util.List;

/**
 * Created by xinming.xxm on 2015/12/4.
 */
public class FragmentMeTabGetDatasEvent {
    private List<BaseItem> mDatas;

    public FragmentMeTabGetDatasEvent(List<BaseItem> mDatas) {
        this.mDatas = mDatas;
    }

    public List<BaseItem> getDatas() {
        return mDatas;
    }
}
