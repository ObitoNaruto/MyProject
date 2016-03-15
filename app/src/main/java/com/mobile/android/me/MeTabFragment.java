package com.mobile.android.me;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.mobile.android.BaseFreagment;
import com.mobile.android.R;
import com.mobile.android.me.event.FragmentMeTabGetDatasEvent;
import com.mobile.android.me.model.BaseItem;
import com.mobile.android.me.adapter.MeTabAdapter;
import com.mobile.android.me.presenter.IFragmentMeTabPresenter;
import com.mobile.android.me.presenter.impl.FragmentMeTabPresenterImpl;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by xinming.xxm on 2015/12/2.
 */
public class MeTabFragment extends BaseFreagment{
    private RecyclerView mRecyclerView;
    private MeTabAdapter mAdapter;
    private IFragmentMeTabPresenter mIFragmentMeTabPresenter;
    @Override
    protected View initRootView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_me_tab, null);
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        mRecyclerView = (RecyclerView)mRootView.findViewById(R.id.rcv_me_tab);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MeTabAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mIFragmentMeTabPresenter = new FragmentMeTabPresenterImpl(getActivity());
        mIFragmentMeTabPresenter.loadDatas();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(FragmentMeTabGetDatasEvent event){
        if(null != event && null != event.getDatas() && event.getDatas().size() > 0){
            mAdapter.setDatas(event.getDatas());
        }
    }
}
