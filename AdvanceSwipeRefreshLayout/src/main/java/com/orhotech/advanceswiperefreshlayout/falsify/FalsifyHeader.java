package com.orhotech.advanceswiperefreshlayout.falsify;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

import com.orhotech.advanceswiperefreshlayout.api.RefreshHeader;
import com.orhotech.advanceswiperefreshlayout.api.RefreshLayout;

/**
 * False Header
 * Used when the real Header is outside RefreshLayout,
 * Use this fake FalsifyHeader to populate inside RefreshLayout
 * For specific usage, please refer to Paper Plane (FlyRefreshHeader)
 * Created by rohit on 2024/10/02.
 */
public class FalsifyHeader extends FalsifyAbstract implements RefreshHeader {

    //<editor-fold desc="FalsifyHeader">
    public FalsifyHeader(Context context) {
        this(context, null);
    }

    public FalsifyHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }
    //</editor-fold>

    //<editor-fold desc="RefreshHeader">
    @Override
    public void onReleased(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
        if (mRefreshKernel != null) {
            /*
             * BUG fixed
             * https://github.com/scwang90/SmartRefreshLayout/issues/1018
             * Strengthen the closing logic of closeHeaderOrFooter to help Header cancel refresh
             * FalsifyHeader cannot trigger refresh
             */
            layout.closeHeaderOrFooter();
//          mRefreshKernel.setState(RefreshState.None);
//          When onReleased, calling setState(RefreshState.None); will not immediately change to None
//          Instead, perform a rebound animation first. RefreshFinish is a state between Refreshing and None.
//          RefreshFinish is used to smoothly change to None at the end of the rebound animation.
//          mRefreshKernel.setState(RefreshState.RefreshFinish);
        }
    }
    //</editor-fold>

}
