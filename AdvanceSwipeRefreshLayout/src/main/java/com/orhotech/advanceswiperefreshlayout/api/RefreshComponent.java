package com.orhotech.advanceswiperefreshlayout.api;

import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import com.orhotech.advanceswiperefreshlayout.constant.SpinnerStyle;
import com.orhotech.advanceswiperefreshlayout.listener.OnStateChangedListener;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;
import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static androidx.annotation.RestrictTo.Scope.SUBCLASSES;

/**
 * Refresh content components
 * Created by rohit on 2024/10/02.
 */
public interface RefreshComponent extends OnStateChangedListener {
    /**
     * Get entity view
     * @return entity view
     */
    @NonNull
    View getView();

    /**
     * Get the transformation method {@link SpinnerStyle} and must return non-empty
     * @return transformation method
     */
    @NonNull
    SpinnerStyle getSpinnerStyle();

    /**
     * [Only called within the frame] Set theme color
     * @param colors corresponds to srlPrimaryColor srlAccentColor configured in Xml
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    void setPrimaryColors(@ColorInt int... colors);

    /**
     * [Only called within the frame] The size definition is completed (if the height does not change (code modification: setHeader), it is only called once, called in RefreshLayout#onMeasure)
     * @param kernel RefreshKernel
     * @param height HeaderHeight or FooterHeight
     * @param maxDragHeight maximum drag height
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight);
    /**
     * [Only called within the frame] Finger drag and drop down (will be called multiple times in succession, add isDragging and replace the previous onPulling, onReleasing)
     * @param isDragging true The finger is dragging false Rebound animation
     * @param percent drop-down percentage value = offset/footerHeight (0 - percent - (footerHeight+maxDragHeight) / footerHeight )
     * @param offset pixel offset of drop-down 0 - offset - (footerHeight+maxDragHeight)
     * @param height Height HeaderHeight or FooterHeight (offset can exceed height and percent is greater than 1)
     * @param maxDragHeight The maximum drag height offset can exceed the height parameter but will not exceed maxDragHeight
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight);

    /**
     * [Only called within the frame] Release time (called once, it will trigger loading)
     * @param refreshLayout RefreshLayout
     * @param height height HeaderHeight or FooterHeight
     * @param maxDragHeight maximum drag height
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight);

    /**
     * [Only called within the frame] Start animation
     * @param refreshLayout RefreshLayout
     * @param height HeaderHeight or FooterHeight
     * @param maxDragHeight maximum drag height
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight);

    /**
     * [Only called within the frame] Animation ends
     * @param refreshLayout RefreshLayout
     * @param success Whether the data is successfully refreshed or loaded
     * @return The time required to complete the animation. If Integer.MAX_VALUE is returned, the completion event will be canceled and the original state will be maintained.
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    int onFinish(@NonNull RefreshLayout refreshLayout, boolean success);

    /**
     * [Only called within the frame] Horizontal dragging
     * @param percentX When pulling down, the proportion of the horizontal coordinates of the finger to the screen (0 - percentX - 1)
     * @param offsetX When pulling down, the offset of the horizontal coordinate of the finger to the screen (0 - offsetX - LayoutWidth)
     * @param offsetMax maximum offset
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    void onHorizontalDrag(float percentX, int offsetX, int offsetMax);

    /**
     * Whether to support horizontal dragging (will affect the call of onHorizontalDrag)
     * @return Horizontal dragging consumes more time and resources, so if it is not supported, please return false
     */
    boolean isSupportHorizontalDrag();

    /**
     * Display refresh animation, Multifunction.
     * Display refresh animation and trigger refresh event
     * @param duration drag animation duration
     * @param dragRate drag height ratio
     * @param animationOnly animation only only animation
     * @return If False is returned, this header does not support automatic refresh
     * Returning False means that this Header does not support automatic refresh.
     */
    boolean autoOpen(int duration, float dragRate, boolean animationOnly);

}
