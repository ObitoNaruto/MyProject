package com.mobile.android.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.android.R;
import com.mobile.android.common.widget.BadgeView;
import com.mobile.android.home.presenter.IHomePresenter;
import com.mobile.android.home.presenter.impl.HomePresenterImpl;
import com.mobile.android.home.view.IHomeView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xinming.xxm on 2015/12/1.
 */
public class MainActivity extends FragmentActivity implements IHomeView{

    private IHomePresenter mHomePresenter;
    private List<Fragment> mDatas = new ArrayList<>();
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;

    private LinearLayout mChatLinearLayout;
    private LinearLayout mContactLinearLayout;
    private LinearLayout mFindLinearLayout;
    private LinearLayout mMeLinearLayout;


    private LinearLayout mmChatLinearLayout;
    private LinearLayout mmContactLinearLayout;
    private LinearLayout mmFindLinearLayout;
    private LinearLayout mmMeLinearLayout;


    //四个文本标签
    private TextView mChatTextView;
    private TextView mContactTextView;
    private TextView mFindTextView;
    private TextView mMeTextView;

    //四个标签对应的图标标签
    private ImageView mChatImageView;
    private ImageView mContactImageView;
    private ImageView mFindImageView;
    private ImageView mMeImageView;

    private ImageView mTabLine;


    private BadgeView mBadgeViewForChat;

    private int widthScreen1_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        mHomePresenter = new HomePresenterImpl(this, this);
        mHomePresenter.loadDatas();

        initTabLine();
    }

    private void initTabLine(){
        mTabLine = (ImageView) findViewById(R.id.iv_tabline);

        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        widthScreen1_4 = displayMetrics.widthPixels / 4;

        ViewGroup.LayoutParams lp = mTabLine.getLayoutParams();
        lp.width = widthScreen1_4;
        mTabLine.setLayoutParams(lp);
    }

    private void initView(){
        //四个文本标签
        mChatTextView = (TextView) findViewById(R.id.tv_chat);
        mContactTextView = (TextView) findViewById(R.id.tv_contact);
        mFindTextView = (TextView) findViewById(R.id.tv_find);
        mMeTextView = (TextView) findViewById(R.id.tv_me);

        //对应的四个图片标签
        mChatImageView = (ImageView) findViewById(R.id.iv_chat);
        mContactImageView = (ImageView) findViewById(R.id.iv_contact);
        mFindImageView = (ImageView) findViewById(R.id.iv_find);
        mMeImageView = (ImageView) findViewById(R.id.iv_me);

        mTabLine = (ImageView) findViewById(R.id.iv_tabline);

        initViewPager();

        mChatLinearLayout = (LinearLayout) findViewById(R.id.ll_chat);
        mContactLinearLayout = (LinearLayout) findViewById(R.id.ll_contact);
        mFindLinearLayout = (LinearLayout) findViewById(R.id.ll_find);
        mMeLinearLayout = (LinearLayout) findViewById(R.id.ll_me);

        mmChatLinearLayout = (LinearLayout) findViewById(R.id.ll_chat_small);
        mmContactLinearLayout = (LinearLayout) findViewById(R.id.ll_contact_small);
        mmFindLinearLayout = (LinearLayout) findViewById(R.id.ll_find_small);
        mmMeLinearLayout = (LinearLayout) findViewById(R.id.ll_me_small);

        BottomLayoutListener listener = new BottomLayoutListener();

        mChatLinearLayout.setOnClickListener(listener);
        mContactLinearLayout.setOnClickListener(listener);
        mFindLinearLayout.setOnClickListener(listener);
        mMeLinearLayout.setOnClickListener(listener);

    }

    private void initViewPager(){
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mDatas);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                        .getLayoutParams();
                lp.leftMargin = (int) ((position + positionOffset) * widthScreen1_4);
                mTabLine.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this, position + "",
                        Toast.LENGTH_SHORT).show();
                resetTextViewColor();
                resetImageViewSrc();
                switch (position) {
                    case 0:
                        if (mBadgeViewForChat != null) {
                            mChatLinearLayout.removeView(mBadgeViewForChat);
                        }
                        mBadgeViewForChat = new BadgeView(MainActivity.this);
                        mBadgeViewForChat.setBadgeCount(13);
                        mBadgeViewForChat.setTargetView(mmChatLinearLayout);
                        mChatTextView.setTextColor(Color.parseColor("#008000"));
                        mChatImageView.setImageResource(R.drawable.chat);
                        break;
                    case 1:
                        mContactTextView.setTextColor(Color.parseColor("#008000"));
                        mContactImageView.setImageResource(R.drawable.contact);
                        break;
                    case 2:
                        mFindTextView.setTextColor(Color.parseColor("#008000"));
                        mFindImageView.setImageResource(R.drawable.find);
                        break;
                    case 3:
                        mMeTextView.setTextColor(Color.parseColor("#008000"));
                        mMeImageView.setImageResource(R.drawable.me);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 重置初始化底部文本标签颜色为白色
     */
    private void resetTextViewColor() {
        mChatTextView.setTextColor(Color.parseColor("#A6A6A6"));
        mContactTextView.setTextColor(Color.parseColor("#A6A6A6"));
        mFindTextView.setTextColor(Color.parseColor("#A6A6A6"));
        mMeTextView.setTextColor(Color.parseColor("#A6A6A6"));
    }

    /**
     * 设置底部图像标签为默认白色背景
     */
    private void resetImageViewSrc() {
        mChatImageView.setImageResource(R.drawable.chat1);
        mContactImageView.setImageResource(R.drawable.contact1);
        mFindImageView.setImageResource(R.drawable.find1);
        mMeImageView.setImageResource(R.drawable.me1);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> list = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        public Fragment getItem(int arg0) {

            return list.get(arg0);
        }

        public int getCount() {
            return list.size();
        }

    }

    class BottomLayoutListener implements View.OnClickListener {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_chat:
                    mViewPager.setCurrentItem(0, true);
                    Toast.makeText(MainActivity.this, "0", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case R.id.ll_contact:
                    mViewPager.setCurrentItem(1, true);
                    Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case R.id.ll_find:
                    mViewPager.setCurrentItem(2, true);
                    Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case R.id.ll_me:
                    mViewPager.setCurrentItem(3, true);
                    Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT)
                            .show();
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onUpdateView(List<Fragment> datas) {
        //这里SB了，竟然用mDatas来做if判断，我屮艸芔茻
        if(null != datas && datas.size() > 0){
            this.mDatas.clear();
            this.mDatas.addAll(datas);
            initView();
        }
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
