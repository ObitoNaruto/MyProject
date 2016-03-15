package com.mobile.android.me.adapterdelegate;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mobile.android.R;
import com.mobile.android.common.adapterdelegate.AbsAdapterDelegate;
import com.mobile.android.common.widget.foldingmenu.FoldingLayout;
import com.mobile.android.common.widget.foldingmenu.OnFoldListener;
import com.mobile.android.me.model.BaseItem;
import com.mobile.android.me.model.TrafficItem;

import java.util.List;

/**
 * Created by xinming.xxm on 2015/12/4.
 */
public class TrafficAdapterDelegate extends AbsAdapterDelegate<List<BaseItem>>{
    private String TAG_ARROW = "service_arrow";
    private final int FOLD_ANIMATION_DURATION = 1000;
    private Activity mActivity;
    private LayoutInflater mInflater;

    public TrafficAdapterDelegate(Activity activity, int viewType) {
        super(viewType);
        this.mActivity = activity;
        this.mInflater = activity.getLayoutInflater();
    }

    @Override
    public boolean isForViewType(@NonNull List<BaseItem> items, int position) {
        return items.get(position) instanceof TrafficItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TrafficViewHolder(mInflater.inflate(R.layout.item_traffic_me, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<BaseItem> items, int position,
                                 @NonNull RecyclerView.ViewHolder holder) {
        final TrafficViewHolder vh = (TrafficViewHolder)holder;
        TrafficItem trafficItem = (TrafficItem)items.get(position);

        vh.mTrafficBarLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                handleAnimation(v, vh.mTrafficFoldingLayout, vh.mTrafficLayout, vh.mBottomView);
            }
        });
    }

    static class TrafficViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout mTrafficLayout;
        private RelativeLayout mTrafficBarLayout;
        private FoldingLayout mTrafficFoldingLayout;

        private View mBottomView;

        public TrafficViewHolder(View itemView) {
            super(itemView);
            mTrafficLayout = (LinearLayout)itemView.findViewById(R.id.traffic_layout);
            mTrafficBarLayout = (RelativeLayout)itemView.findViewById(R.id.traffic_bar_layout);
            mTrafficFoldingLayout = (FoldingLayout)itemView.findViewById(R.id.traffic_item);
            mBottomView = itemView.findViewById(R.id.bottom_view);
        }
    }

    private void handleAnimation(final View bar, final FoldingLayout foldingLayout, View parent, final View nextParent) {

        final ImageView arrow = (ImageView) parent.findViewWithTag(TAG_ARROW);

        foldingLayout.setFoldListener(new OnFoldListener() {

            @Override
            public void onStartFold(float foldFactor) {

                bar.setClickable(true);
                arrow.setBackgroundResource(R.drawable.service_arrow_up);
                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }

            @Override
            public void onFoldingState(float foldFactor, float foldDrawHeight) {
                bar.setClickable(false);
                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }

            @Override
            public void onEndFold(float foldFactor) {

                bar.setClickable(true);
                arrow.setBackgroundResource(R.drawable.service_arrow_down);
                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }
        });

//      foldingLayout.setNumberOfFolds(mNumberOfFolds);
        animateFold(foldingLayout, FOLD_ANIMATION_DURATION);

    }

    private void resetMarginToTop(View view, float foldFactor, View nextParent) {

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) nextParent.getLayoutParams();
        lp.topMargin =(int)( - view.getMeasuredHeight() * foldFactor) + dp2px(mActivity, 10);
        nextParent.setLayoutParams(lp);
    }

    public final static int dp2px(Context context, float dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    @SuppressLint("NewApi")
    public void animateFold(FoldingLayout foldLayout, int duration) {
        float foldFactor = foldLayout.getFoldFactor();

        ObjectAnimator animator = ObjectAnimator.ofFloat(foldLayout,
                "foldFactor", foldFactor, foldFactor > 0 ? 0 : 1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(0);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }
}
