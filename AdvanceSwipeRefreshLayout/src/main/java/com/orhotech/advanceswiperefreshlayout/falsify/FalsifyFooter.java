package com.orhotech.advanceswiperefreshlayout.falsify;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

import com.orhotech.advanceswiperefreshlayout.api.RefreshFooter;
import com.orhotech.advanceswiperefreshlayout.api.RefreshKernel;
import com.orhotech.advanceswiperefreshlayout.api.RefreshLayout;

/**
 * Fake Footer
 * Used when the real Footer is outside RefreshLayout,
 * Created by rohit on 2024/10/02.
 */
@SuppressWarnings("unused")
public class FalsifyFooter extends FalsifyAbstract implements RefreshFooter {

    //<editor-fold desc="FalsifyHeader">
    public FalsifyFooter(Context context) {
        this(context, null);
    }

    public FalsifyFooter(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }
    //</editor-fold>

    //<editor-fold desc="RefreshFooter">
    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        mRefreshKernel = kernel;
        kernel.getRefreshLayout().setEnableAutoLoadMore(false);
    }

    @Override
    public void onReleased(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
        if (mRefreshKernel != null) {
            /*
             * 2020-3-15 BUG fixed
             * https://github.com/scwang90/SmartRefreshLayout/issues/1018
             * Strengthen the closing logic of closeHeaderOrFooter to help Footer cancel refresh
             * FalsifyFooter cannot trigger loading
             */
            layout.closeHeaderOrFooter();
//          mRefreshKernel.setState(RefreshState.None);
//          When onReleased, calling setState(RefreshState.None); will not immediately change to None
//          Instead, a rebound animation is executed first. LoadFinish is in a state between Refreshing and None.
//          LoadFinish is used to smoothly change to None at the end of the rebound animation
//          mRefreshKernel.setState(RefreshState.LoadFinish);
        }
    }
    //</editor-fold>

}
