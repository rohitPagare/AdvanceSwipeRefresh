package com.orhotech.advanceswiperefreshlayout.wrapper;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.PointF;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.Space;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingParent;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.orhotech.advanceswiperefreshlayout.api.RefreshContent;
import com.orhotech.advanceswiperefreshlayout.api.RefreshKernel;
import com.orhotech.advanceswiperefreshlayout.R;
import com.orhotech.advanceswiperefreshlayout.listener.CoordinatorLayoutListener;
import com.orhotech.advanceswiperefreshlayout.listener.ScrollBoundaryDecider;
import com.orhotech.advanceswiperefreshlayout.simple.SimpleBoundaryDecider;
import com.orhotech.advanceswiperefreshlayout.util.DesignUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.orhotech.advanceswiperefreshlayout.util.SmartUtil.isContentView;
import static com.orhotech.advanceswiperefreshlayout.util.SmartUtil.isTransformedTouchPointInView;
import static com.orhotech.advanceswiperefreshlayout.util.SmartUtil.measureViewHeight;
import static com.orhotech.advanceswiperefreshlayout.util.SmartUtil.scrollListBy;

/**
 *
 * Created by rohit on 2024/10/02.
 */
@SuppressWarnings("WeakerAccess")
public class RefreshContentWrapper implements RefreshContent, CoordinatorLayoutListener, AnimatorUpdateListener {

    protected View mContentView;//direct content view
    protected View mOriginalContentView;//The wrapped original real view
    protected View mScrollableView;
    protected View mFixedHeader;
    protected View mFixedFooter;
    protected int mLastSpinner = 0;
    protected boolean mEnableRefresh = true;
    protected boolean mEnableLoadMore = true;
    protected SimpleBoundaryDecider mBoundaryAdapter = new SimpleBoundaryDecider();

    public RefreshContentWrapper(@NonNull View view) {
        this.mContentView = mOriginalContentView = mScrollableView = view;
    }

    //<editor-fold desc="findScrollableView">
    protected void findScrollableView(View content, RefreshKernel kernel) {
        View scrollableView = null;
        boolean isInEditMode = mContentView.isInEditMode();
        while (scrollableView == null || (scrollableView instanceof NestedScrollingParent
                && !(scrollableView instanceof NestedScrollingChild))) {
            content = findScrollableViewInternal(content, scrollableView == null);
            if (content == scrollableView) {
                break;
            }
            if (!isInEditMode) {
                DesignUtil.checkCoordinatorLayout(content, kernel, this);
            }
            scrollableView = content;
        }
        if (scrollableView != null) {
            mScrollableView = scrollableView;
        }
    }

    @Override
    public void onCoordinatorUpdate(boolean enableRefresh, boolean enableLoadMore) {
        mEnableRefresh = enableRefresh;
        mEnableLoadMore = enableLoadMore;
    }

    protected View findScrollableViewInternal(View content, boolean selfAble) {
        View scrollableView = null;
        Queue<View> views = new LinkedList<>();
        //noinspection unchecked
        List<View> list = (List<View>)views;
        list.add(content);
        while (list.size() > 0 && scrollableView == null) {
            View view = views.poll();
            if (view != null) {
                if ((selfAble || view != content) && isContentView(view)) {
                    scrollableView = view;
                } else if (view instanceof ViewGroup) {
                    ViewGroup group = (ViewGroup) view;
                    for (int j = 0; j < group.getChildCount(); j++) {
                        list.add(group.getChildAt(j));
                    }
                }
            }
        }
        return scrollableView == null ? content : scrollableView;
    }

    protected View findScrollableViewByPoint(View content, PointF event, View orgScrollableView) {
        if (content instanceof ViewGroup && event != null) {
            ViewGroup viewGroup = (ViewGroup) content;
            final int childCount = viewGroup.getChildCount();
            PointF point = new PointF();
            for (int i = childCount; i > 0; i--) {
                View child = viewGroup.getChildAt(i - 1);
                if (isTransformedTouchPointInView(viewGroup, child, event.x, event.y, point)) {
                    if (child instanceof ViewPager || child instanceof ViewPager2 || !isContentView(child)) {
                        event.offset(point.x, point.y);
                        child = findScrollableViewByPoint(child, event, orgScrollableView);
                        event.offset(-point.x, -point.y);
                    }
                    return child;
                }
            }
        }
        return orgScrollableView;
    }
    //</editor-fold>

    //<editor-fold desc="implements">
    @NonNull
    public View getView() {
        return mContentView;
    }

    @Override
    @NonNull
    public View getScrollableView() {
        return mScrollableView;
    }

    @Override
    public void moveSpinner(int spinner, int headerTranslationViewId, int footerTranslationViewId) {
        boolean translated = false;
        if (headerTranslationViewId != View.NO_ID) {
            View headerTranslationView = mOriginalContentView.findViewById(headerTranslationViewId);
            if (headerTranslationView != null) {
                if (spinner > 0) {
                    translated = true;
                    headerTranslationView.setTranslationY(spinner);
                } else if (headerTranslationView.getTranslationY() > 0) {
                    headerTranslationView.setTranslationY(0);
                }
            }
        }
        if (footerTranslationViewId != View.NO_ID) {
            View footerTranslationView = mOriginalContentView.findViewById(footerTranslationViewId);
            if (footerTranslationView != null) {
                if (spinner < 0) {
                    translated = true;
                    footerTranslationView.setTranslationY(spinner);
                } else if (footerTranslationView.getTranslationY() < 0) {
                    footerTranslationView.setTranslationY(0);
                }
            }
        }
        if (!translated) {
            mOriginalContentView.setTranslationY(spinner);
        } else {
            mOriginalContentView.setTranslationY(0);
        }
        if (mFixedHeader != null) {
            mFixedHeader.setTranslationY(Math.max(0, spinner));
        }
        if (mFixedFooter != null) {
            mFixedFooter.setTranslationY(Math.min(0, spinner));
        }
    }

    @Override
    public boolean canRefresh() {
        return mEnableRefresh && mBoundaryAdapter.canRefresh(mContentView);
    }

    @Override
    public boolean canLoadMore() {
        return mEnableLoadMore && mBoundaryAdapter.canLoadMore(mContentView);
    }

    @Override
    public void onActionDown(MotionEvent e) {
        PointF point = new PointF(e.getX(), e.getY());
        point.offset(-mContentView.getLeft(), -mContentView.getTop());
        if (mScrollableView != mContentView) {
            //If the content view is not a ScrollableView, it means that Layout nested content is used and a ScrollableView needs to be dynamically searched.
            mScrollableView = findScrollableViewByPoint(mContentView, point, mScrollableView);
        }
        if (mScrollableView == mContentView) {
            //If the content view is a ScrollableView, there is no need to use events to dynamically search and waste CPU time and performance.
//            mBoundaryAdapter.setActionEvent(null);
            mBoundaryAdapter.mActionEvent = null;
        } else {
            mBoundaryAdapter.mActionEvent = point;
//            mBoundaryAdapter.setActionEvent(mMotionEvent);
        }
    }

    @Override
    public void setUpComponent(RefreshKernel kernel, View fixedHeader, View fixedFooter) {
        findScrollableView(mContentView, kernel);

        if (fixedHeader != null || fixedFooter != null) {
            mFixedHeader = fixedHeader;
            mFixedFooter = fixedFooter;
            ViewGroup frameLayout = new FrameLayout(mContentView.getContext());
            int index = kernel.getRefreshLayout().getLayout().indexOfChild(mContentView);
            kernel.getRefreshLayout().getLayout().removeView(mContentView);
            frameLayout.addView(mContentView, 0, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
            kernel.getRefreshLayout().getLayout().addView(frameLayout, index, layoutParams);
            mContentView = frameLayout;
            if (fixedHeader != null) {
                fixedHeader.setTag(R.id.srl_tag, "fixed-top");
                ViewGroup.LayoutParams lp = fixedHeader.getLayoutParams();
                ViewGroup parent = (ViewGroup) fixedHeader.getParent();
                index = parent.indexOfChild(fixedHeader);
                parent.removeView(fixedHeader);
                lp.height = measureViewHeight(fixedHeader);
                parent.addView(new Space(mContentView.getContext()), index, lp);
                frameLayout.addView(fixedHeader, 1, lp);
            }
            if (fixedFooter != null) {
                fixedFooter.setTag(R.id.srl_tag,"fixed-bottom");
                ViewGroup.LayoutParams lp = fixedFooter.getLayoutParams();
                ViewGroup parent = (ViewGroup) fixedFooter.getParent();
                index = parent.indexOfChild(fixedFooter);
                parent.removeView(fixedFooter);
                FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(lp);
                lp.height = measureViewHeight(fixedFooter);
                parent.addView(new Space(mContentView.getContext()), index, lp);
                flp.gravity = Gravity.BOTTOM;
                frameLayout.addView(fixedFooter, 1, flp);
            }
        }
    }

    @Override
    public void setScrollBoundaryDecider(ScrollBoundaryDecider boundary) {
        if (boundary instanceof SimpleBoundaryDecider) {
            mBoundaryAdapter = ((SimpleBoundaryDecider) boundary);
        } else {
            mBoundaryAdapter.boundary = (boundary);
        }
    }

    @Override
    public void setEnableLoadMoreWhenContentNotFull(boolean enable) {
        mBoundaryAdapter.mEnableLoadMoreWhenContentNotFull = enable;
    }

    @Override
    public AnimatorUpdateListener scrollContentWhenFinished(final int spinner) {
        if (mScrollableView != null && spinner != 0) {
            if ((spinner < 0 && mScrollableView.canScrollVertically(1)) || (spinner > 0 && mScrollableView.canScrollVertically(-1))) {
                mLastSpinner = spinner;
                return this;
            }
        }
        return null;
    }

    @Override
    public void onAnimationUpdate(@NonNull ValueAnimator animation) {
        int value = (int) animation.getAnimatedValue();
        try {
            float dy = (value - mLastSpinner) * mScrollableView.getScaleY();
            if (mScrollableView instanceof AbsListView) {
                scrollListBy((AbsListView) mScrollableView, (int)dy);
            } else {
                mScrollableView.scrollBy(0, (int)dy);
            }
        } catch (Throwable e) {
            //Based on user feedback, there may be BUG here
            e.printStackTrace();
        }
        mLastSpinner = value;
    }
    //</editor-fold>

}
