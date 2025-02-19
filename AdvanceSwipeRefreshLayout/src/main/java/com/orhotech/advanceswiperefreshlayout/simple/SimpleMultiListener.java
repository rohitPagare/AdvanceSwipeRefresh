package com.orhotech.advanceswiperefreshlayout.simple;

import androidx.annotation.NonNull;

import com.orhotech.advanceswiperefreshlayout.api.RefreshFooter;
import com.orhotech.advanceswiperefreshlayout.api.RefreshHeader;
import com.orhotech.advanceswiperefreshlayout.api.RefreshLayout;
import com.orhotech.advanceswiperefreshlayout.constant.RefreshState;
import com.orhotech.advanceswiperefreshlayout.listener.OnMultiListener;

/**
 * Multifunctional monitor
 * Created by rohit on 2024/10/02.
 */
public class SimpleMultiListener implements OnMultiListener {

    @Override
    public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderStartAnimator(RefreshHeader header, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderFinish(RefreshHeader header, boolean success) {

    }

    @Override
    public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterStartAnimator(RefreshFooter footer, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterFinish(RefreshFooter footer, boolean success) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }

}
