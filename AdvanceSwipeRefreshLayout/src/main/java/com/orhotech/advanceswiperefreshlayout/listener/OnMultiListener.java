package com.orhotech.advanceswiperefreshlayout.listener;

import com.orhotech.advanceswiperefreshlayout.api.RefreshFooter;
import com.orhotech.advanceswiperefreshlayout.api.RefreshHeader;

/**
 * Multifunctional monitor
 * Created by rohit on 2024/10/02.
 */
public interface OnMultiListener extends OnRefreshLoadMoreListener, OnStateChangedListener {
    /**
     * Finger drag and drop down (will be called multiple times in succession, add isDragging and replace the previous onPulling, onReleasing)
     * @param header header
     * @param isDragging true The finger is dragging false Rebound animation
     * @param percent drop-down percentage value = offset/footerHeight (0 - percent - (footerHeight+maxDragHeight) / footerHeight )
     * @param offset pixel offset of drop-down 0 - offset - (footerHeight+maxDragHeight)
     * @param headerHeight height HeaderHeight or FooterHeight
     * @param maxDragHeight maximum drag height
     */
    void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight);

    void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight);
    void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight);
    void onHeaderFinish(RefreshHeader header, boolean success);

    /**
     * Finger drag and pull up (will be called multiple times in succession, add isDragging and replace the previous onPulling, onReleasing)
     * @param footer tail
     * @param isDragging true The finger is dragging false Rebound animation
     * @param percent drop-down percentage value = offset/footerHeight (0 - percent - (footerHeight+maxDragHeight) / footerHeight )
     * @param offset pixel offset of drop-down 0 - offset - (footerHeight+maxDragHeight)
     * @param footerHeight height HeaderHeight or FooterHeight
     * @param maxDragHeight maximum drag height
     */
    void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight);

    void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight);
    void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight);
    void onFooterFinish(RefreshFooter footer, boolean success);
}
