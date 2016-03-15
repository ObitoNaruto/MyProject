package com.mobile.android.me.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mobile.android.common.adapterdelegate.AdapterDelegatesManager;
import com.mobile.android.me.adapterdelegate.TrafficAdapterDelegate;
import com.mobile.android.me.model.BaseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinming.xxm on 2015/12/4.
 */
public class MeTabAdapter extends RecyclerView.Adapter{

    private AdapterDelegatesManager<List<BaseItem>> mDelegatesManager;
    private List<BaseItem> mItems;

    public MeTabAdapter(Activity activity) {
        this.mItems = new ArrayList<>();
        mDelegatesManager = new AdapterDelegatesManager<>();

        mDelegatesManager.addDelegate(new TrafficAdapterDelegate(activity, 0));
    }

    public void setDatas(List<BaseItem> items){
        if(null != items && items.size() > 0){
            this.mItems.clear();
            this.mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegatesManager.getItemViewType(mItems, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mDelegatesManager.onBindViewHolder(mItems, position, holder);
    }

    @Override
    public int getItemCount() {
        return null != mItems && mItems.size() > 0 ? mItems.size() : 0;
    }
}
