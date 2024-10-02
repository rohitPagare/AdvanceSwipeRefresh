package com.orhotech.advanceswiperefreshlayout.api;

import android.animation.Animator;
import android.animation.ValueAnimator;

import androidx.annotation.NonNull;

import com.orhotech.advanceswiperefreshlayout.constant.RefreshState;

/**
 * Refresh layout core function interface
 * Open interface for Header or Footer with complex functions
 * Created by rohit on 2024/10/02.
 */
@SuppressWarnings({"unused", "UnusedReturnValue", "SameParameterValue"})
public interface RefreshKernel {

    @NonNull
    RefreshLayout getRefreshLayout();
    @NonNull
    RefreshContent getRefreshContent();

    RefreshKernel setState(@NonNull RefreshState state);

    //<editor-fold desc="View displacement Spinner">

    /**
     * Start executing two-pole refresh
     * @param open whether to expand
     * @return RefreshKernel
     */
    RefreshKernel startTwoLevel(boolean open);

    /**
     * End and close diode refresh
     * @return RefreshKernel
     */
    RefreshKernel finishTwoLevel();

    /**
     * Move the view to the specified position
     * The name of moveSpinner comes from Google’s official {@link androidx.swiperefreshlayout.widget.SwipeRefreshLayout}
     * @param spinner position (px)
     * @param isDragging true The finger is dragging false The rebound animation is executed
     * @return RefreshKernel
     */
    RefreshKernel moveSpinner(int spinner, boolean isDragging);

    /**
     * Execute animation to move the view to the specified position
     * The name of moveSpinner comes from Google’s official {@link SwipeRefreshLayout}
     * @param endSpinner specified end position (px)
     * @return ValueAnimator null if animation is not executed
     */
    ValueAnimator animSpinner(int endSpinner);
    //</editor-fold>

    //<editor-fold desc="Request event">

    /**
     * Specify to draw the background for Header or Footer when pulling down
     * @param internal Header Footer pass this when calling
     * @param backgroundColor background color
     * @return RefreshKernel
     */
    RefreshKernel requestDrawBackgroundFor(@NonNull RefreshComponent internal, int backgroundColor);
    /**
     * Request event
     * @param internal Header Footer pass this when calling
     * @param request request
     * @return RefreshKernel
     */
    RefreshKernel requestNeedTouchEventFor(@NonNull RefreshComponent internal, boolean request);
    /**
     * Request to set default content scrolling settings
     * @param internal Header Footer pass this when calling
     * @param translation mobile
     * @return RefreshKernel
     */
    RefreshKernel requestDefaultTranslationContentFor(@NonNull RefreshComponent internal, boolean translation);
    /**
     * Request to remeasure headerHeight or footerHeight, requiring height to be WRAP_CONTENT
     * @param internal Header Footer pass this when calling
     * @return RefreshKernel
     */
    RefreshKernel requestRemeasureHeightFor(@NonNull RefreshComponent internal);
    /**
     *Set the second floor rebound time
     * @param duration The second floor rebound duration
     * @return RefreshKernel
     */
    RefreshKernel requestFloorDuration(int duration);
    /**
     * Set the ratio of the height occupied by the upward stroke at the bottom of the second floor
     * @return RefreshKernel
     */
    RefreshKernel requestFloorBottomPullUpToCloseRate(float rate);
    /**
     * When the autoRefresh animation ends, handle the refresh state event
     * @param animation animation object
     * @param animationOnly Whether to only play animations without notifying events
     * @return RefreshKernel
     */
    RefreshKernel onAutoRefreshAnimationEnd(Animator animation, boolean animationOnly);
    /**
     * When the autoLoadMore animation ends, handle the refresh state event
     * @param animation animation object
     * @param animationOnly Whether to only play animations without notifying events
     * @return RefreshKernel
     */
    RefreshKernel onAutoLoadMoreAnimationEnd(Animator animation, boolean animationOnly);
    //</editor-fold>
}
