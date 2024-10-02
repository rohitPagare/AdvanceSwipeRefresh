package com.orhotech.advanceswiperefreshlayout.listener;

import android.view.View;

/**
 * scrolling border
 * Created by rohit on 2024/10/02.
 */
public interface ScrollBoundaryDecider {
    /**
     * Determine whether pull-down refresh can be started based on the content view status
     * @param content content view
     * @return true will trigger pull-down refresh
     */
    boolean canRefresh(View content);
    /**
     * Determine whether pull-up loading can be started based on the content view status
     * @param content content view
     * @return true will trigger loading more
     */
    boolean canLoadMore(View content);
}
