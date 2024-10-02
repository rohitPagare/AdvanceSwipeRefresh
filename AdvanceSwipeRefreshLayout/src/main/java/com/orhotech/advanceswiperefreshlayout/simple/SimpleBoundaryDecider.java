package com.orhotech.advanceswiperefreshlayout.simple;

import android.graphics.PointF;
import android.view.View;

import com.orhotech.advanceswiperefreshlayout.listener.ScrollBoundaryDecider;
import com.orhotech.advanceswiperefreshlayout.util.SmartUtil;

/**
 * scrolling border
 * Created by rohit on 2024/10/02.
 */
public class SimpleBoundaryDecider implements ScrollBoundaryDecider {

    //<editor-fold desc="Internal">
    public PointF mActionEvent;
    public ScrollBoundaryDecider boundary;
    public boolean mEnableLoadMoreWhenContentNotFull = true;
    //</editor-fold>

    //<editor-fold desc="ScrollBoundaryDecider">
    @Override
    public boolean canRefresh(View content) {
        if (boundary != null) {
            return boundary.canRefresh(content);
        }
        //canRefresh will not dynamically search recursively when mActionEvent == null
        return SmartUtil.canRefresh(content, mActionEvent);
    }

    @Override
    public boolean canLoadMore(View content) {
        if (boundary != null) {
            return boundary.canLoadMore(content);
        }
        //canLoadMore will not dynamically search recursively when mActionEvent == null
        return SmartUtil.canLoadMore(content, mActionEvent, mEnableLoadMoreWhenContentNotFull);
    }
    //</editor-fold>
}
