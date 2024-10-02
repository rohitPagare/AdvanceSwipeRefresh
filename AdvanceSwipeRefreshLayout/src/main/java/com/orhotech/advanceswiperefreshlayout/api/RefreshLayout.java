package com.orhotech.advanceswiperefreshlayout.api;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.orhotech.advanceswiperefreshlayout.constant.RefreshState;
import com.orhotech.advanceswiperefreshlayout.listener.OnLoadMoreListener;
import com.orhotech.advanceswiperefreshlayout.listener.OnMultiListener;
import com.orhotech.advanceswiperefreshlayout.listener.OnRefreshListener;
import com.orhotech.advanceswiperefreshlayout.listener.OnRefreshLoadMoreListener;
import com.orhotech.advanceswiperefreshlayout.listener.ScrollBoundaryDecider;

/**
 * refresh layout
 * interface of the refresh layout
 * Created by rohit on 2024/10/02.
 */
@SuppressWarnings({"UnusedReturnValue", "SameParameterValue", "unused"})
public interface RefreshLayout {

    /**
     * Set the Footer's height.
     * Set the height of Footer
     * @param dp Density-independent Pixels virtual pixels (px needs to call px2dp conversion)
     * @return RefreshLayout
     */
    RefreshLayout setFooterHeight(float dp);

    /**
     * Set Footer height
     * @param px pixels
     * @return RefreshLayout
     */
    RefreshLayout setFooterHeightPx(int px);

    /**
     * Set the Header's height.
     * Set Header height
     * @param dp Density-independent Pixels virtual pixels (px needs to call px2dp conversion)
     * @return RefreshLayout
     */
    RefreshLayout setHeaderHeight(float dp);

    /**
     * Set Header height
     * @param px pixels
     * @return RefreshLayout
     */
    RefreshLayout setHeaderHeightPx(int px);

    /**
     * Set the Header's start offset (see srlHeaderInsetStart in the RepastPracticeActivity XML in demo-app for the practical application).
     * Set the starting offset of Header (for usage, please refer to srlHeaderInsetStart in RepastPracticeActivity xml in demo-app)
     * @param dp Density-independent Pixels virtual pixels (px needs to call px2dp conversion)
     * @return RefreshLayout
     */
    RefreshLayout setHeaderInsetStart(float dp);

    /**
     * Set the Header's start offset (see srlHeaderInsetStart in the RepastPracticeActivity XML in demo-app for the practical application).
     * Set Header starting offset (for usage method, please refer to srlHeaderInsetStart in RepastPracticeActivity xml in demo-app)
     * @param px pixels
     * @return RefreshLayout
     */
    RefreshLayout setHeaderInsetStartPx(int px);

    /**
     * Set the Footer's start offset.
     * Set the Footer starting offset (same use as setHeaderInsetStart)
     * @see RefreshLayout#setHeaderInsetStart(float)
     * @param dp Density-independent Pixels virtual pixels (px needs to call px2dp conversion)
     * @return RefreshLayout
     */
    RefreshLayout setFooterInsetStart(float dp);

    /**
     * Set the Footer's start offset.
     * Set the Footer starting offset (same use as setFooterInsetStartPx)
     * @param px pixels
     * @return RefreshLayout
     */
    RefreshLayout setFooterInsetStartPx(int px);

    /**
     * Set the damping effect.
     * Display drag height/real drag height ratio (default 0.5, damping effect)
     * @param rate ratio = (The drag height of the view)/(The actual drag height of the finger)
     * Ratio = view drag height / finger drag height
     * @return RefreshLayout
     */
    RefreshLayout setDragRate(@FloatRange(from = 0, to = 1) float rate);

    /**
     * Set the ratio of the maximum height to drag header.
     * Set the ratio of the maximum drop-down height to the Header height (will affect the maximum drop-down height)
     * @param rate ratio = (the maximum height to drag header)/(the height of header)
     * Ratio = drop-down maximum height / Header height
     * @return RefreshLayout
     */
    RefreshLayout setHeaderMaxDragRate(@FloatRange(from = 1, to = 10) float rate);

    /**
     * Set the ratio of the maximum height to drag footer.
     * Set the ratio of the maximum pull-up height to the Footer height (will affect the maximum pull-up height)
     * @param rate ratio = (the maximum height to drag footer)/(the height of footer)
     * Ratio = Maximum drop-down height / Footer height
     * @return RefreshLayout
     */
    RefreshLayout setFooterMaxDragRate(@FloatRange(from = 1, to = 10) float rate);

    /**
     * Set the ratio at which the refresh is triggered.
     * Set the ratio of trigger refresh distance to HeaderHeight
     * @param rate The ratio of trigger refresh distance to HeaderHeight
     * @return RefreshLayout
     */
    RefreshLayout setHeaderTriggerRate(@FloatRange(from = 0, to = 1.0) float rate);

    /**
     * Set the ratio at which the load more is triggered.
     * Set the ratio of trigger loading distance to FooterHeight
     * @param rate The ratio of trigger loading distance to FooterHeight
     * @return RefreshLayout
     */
    RefreshLayout setFooterTriggerRate(@FloatRange(from = 0, to = 1.0) float rate);

    /**
     * Set the rebound interpolator.
     * Set the rebound display interpolator [rebound animation when letting go, shrink animation when ending]
     * @param interpolator animation interpolator
     * @return RefreshLayout
     */
    RefreshLayout setReboundInterpolator(@NonNull Interpolator interpolator);

    /**
     * Set the duration of the rebound animation.
     * Set the rebound animation duration [rebound animation when letting go, shrink animation when it ends]
     * @param duration duration
     * @return RefreshLayout
     */
    RefreshLayout setReboundDuration(int duration);

    /**
     * Set the footer of RefreshLayout.
     * Set the specified Footer
     * @param footer RefreshFooter Refresh the tail
     * @return RefreshLayout
     */
    RefreshLayout setRefreshFooter(@NonNull RefreshFooter footer);

    /**
     * Set the footer of RefreshLayout.
     * Set the specified Footer
     * @param footer RefreshFooter Refresh the tail
     * @param width the width in px, can use MATCH_PARENT and WRAP_CONTENT.
     * Width can use MATCH_PARENT, WRAP_CONTENT
     * @param height the height in px, can use MATCH_PARENT and WRAP_CONTENT.
     * Height can use MATCH_PARENT, WRAP_CONTENT
     * @return RefreshLayout
     */
    RefreshLayout setRefreshFooter(@NonNull RefreshFooter footer, int width, int height);

    /**
     * Set the header of RefreshLayout.
     * Set the specified Header
     * @param header RefreshHeader refresh header
     * @return RefreshLayout
     */
    RefreshLayout setRefreshHeader(@NonNull RefreshHeader header);

    /**
     * Set the header of RefreshLayout.
     * Set the specified Header
     * @param header RefreshHeader refresh header
     * @param width the width in px, can use MATCH_PARENT and WRAP_CONTENT.
     * Width can use MATCH_PARENT, WRAP_CONTENT
     * @param height the height in px, can use MATCH_PARENT and WRAP_CONTENT.
     * Height can use MATCH_PARENT, WRAP_CONTENT
     * @return RefreshLayout
     */
    RefreshLayout setRefreshHeader(@NonNull RefreshHeader header, int width, int height);

    /**
     * Set the content of RefreshLayout (Suitable for non-XML pages, not suitable for replacing empty layouts).
     * Set the specified Content (applicable to non-XML pages, not suitable for replacing empty layouts)
     * @param content View content view
     * @return RefreshLayout
     */
    RefreshLayout setRefreshContent(@NonNull View content);

    /**
     * Set the content of RefreshLayout (Suitable for non-XML pages, not suitable for replacing empty layouts).
     * Set the specified Content (applicable to non-XML pages, not suitable for replacing empty layouts)
     * @param content View content view
     * @param width the width in px, can use MATCH_PARENT and WRAP_CONTENT.
     * Width can use MATCH_PARENT, WRAP_CONTENT
     * @param height the height in px, can use MATCH_PARENT and WRAP_CONTENT.
     * Height can use MATCH_PARENT, WRAP_CONTENT
     * @return RefreshLayout
     */
    RefreshLayout setRefreshContent(@NonNull View content, int width, int height);

    /**
     * Whether to enable pull-down refresh (enabled by default).
     * Whether to enable pull-down refresh (enabled by default)
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableRefresh(boolean enabled);

    /**
     * Set whether to enable pull-up loading more (enabled by default).
     * Set whether to enable pull-up to load more (enabled by default)
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableLoadMore(boolean enabled);

    /**
     * Sets whether to listen for the list to trigger a load event when scrolling to the bottom (default true).
     * Set whether the listening list triggers a loading event when it scrolls to the bottom (default true)
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableAutoLoadMore(boolean enabled);

    /**
     * Set whether to pull down the content while pulling down the header.
     * Set whether to enable the content to be pulled down while pulling down the Header
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableHeaderTranslationContent(boolean enabled);

    /**
     * Set whether to pull up the content while pulling up the header.
     * Set whether to enable pulling up the content while pulling up the Footer
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableFooterTranslationContent(boolean enabled);

    /**
     * Set whether to enable cross-border rebound function.
     * Set whether to enable cross-border rebound
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableOverScrollBounce(boolean enabled);

    /**
     * Set whether to enable the pure scroll mode.
     * Set whether to enable pure scrolling mode
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnablePureScrollMode(boolean enabled);

    /**
     * Set whether to scroll the content to display new data after loading more complete.
     * Set whether to scroll the content to display new data after loading more is completed
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableScrollContentWhenLoaded(boolean enabled);

    /**
     * Set whether to scroll the content to display new data after the refresh is complete.
     * Whether to scroll the content to display new data after the refresh is completed
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableScrollContentWhenRefreshed(boolean enabled);

    /**
     * Set whether to pull up and load more when the content is not full of one page.
     * Set whether the content can be pulled up to load more when the content is less than one page.
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableLoadMoreWhenContentNotFull(boolean enabled);

    /**
     * Set whether to enable cross-border drag (imitation iphone effect).
     * Set whether to enable cross-border dragging (imitating Apple effect)
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableOverScrollDrag(boolean enabled);

    /**
     * Set whether or not Footer follows the content after there is no more data.
     * Set whether the Footer follows the content after there is no more data
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableFooterFollowWhenNoMoreData(boolean enabled);

    /**
     * Set whether to clip header when the Header is in the FixedBehind state.
     * Set whether to clip and block the Header when the Header is in the FixedBehind state
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableClipHeaderWhenFixedBehind(boolean enabled);

    /**
     * Set whether to clip footer when the Footer is in the FixedBehind state.
     * Set whether to clip the occlusion Footer when the Footer is in the FixedBehind state
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableClipFooterWhenFixedBehind(boolean enabled);

    /**
     * Setting whether nesting scrolling is enabled (default off + smart on).
     * Set whether to enable the nested scrolling function (default off + smart on)
     * @param enabled Is it enabled?
     * @return RefreshLayout
     */
    RefreshLayout setEnableNestedScroll(boolean enabled);
    /**
     * Set the view ID fixed under the Header to keep it from scrolling when the Footer scrolls up and down.
     * @param id The view ID fixed at the head
     * @return RefreshLayout
     */
    RefreshLayout setFixedHeaderViewId(@IdRes int id);
    /**
     * Set the view ID fixed above the Footer to keep the Header from scrolling when it scrolls up and down.
     * @param id The view ID fixed at the bottom
     * @return RefreshLayout
     */
    RefreshLayout setFixedFooterViewId(@IdRes int id);
    /**
     * Set the view ID that needs to follow the scrolling when scrolling up and down in the Header. The default is the entire content view.
     * @param id The view ID fixed at the head
     * @return RefreshLayout
     */
    RefreshLayout setHeaderTranslationViewId(@IdRes int id);
    /**
     * Set the view ID that needs to follow the scrolling when the Footer scrolls up and down. The default is the entire content view.
     * @param id The view ID fixed at the head
     * @return RefreshLayout
     */
    RefreshLayout setFooterTranslationViewId(@IdRes int id);
    /**
     * Set whether to enable the action content view when refreshing.
     * Set whether to enable disabling operations on the content view when refreshing
     * @param disable whether to disable
     * @return RefreshLayout
     */
    RefreshLayout setDisableContentWhenRefresh(boolean disable);

    /**
     * Set whether to enable the action content view when loading.
     * Set whether to enable the prohibition of operating the content view during loading
     * @param disable whether to disable
     * @return RefreshLayout
     */
    RefreshLayout setDisableContentWhenLoading(boolean disable);

    /**
     * Set refresh listener separately.
     * Set refresh listener separately
     * @param listener OnRefreshListener refresh listener
     * @return RefreshLayout
     */
    RefreshLayout setOnRefreshListener(OnRefreshListener listener);

    /**
     * Set load more listener separately.
     * Set up loading listener separately
     * @param listener OnLoadMoreListener loading listener
     * @return RefreshLayout
     */
    RefreshLayout setOnLoadMoreListener(OnLoadMoreListener listener);

    /**
     * Set refresh and load listeners at the same time.
     * Set refresh and load listeners at the same time
     * @param listener OnRefreshLoadMoreListener refresh load listener
     * @return RefreshLayout
     */
    RefreshLayout setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener listener);

    /**
     * Set up a multi-function listener.
     * Recommended {@link com.orhotech.advanceswiperefreshlayout.simple.SimpleMultiListener}
     * Set up multi-function monitor
     * It is recommended to use {@link com.orhotech.advanceswiperefreshlayout.simple.SimpleMultiListener}
     * @param listener OnMultiPurposeListener multi-function listener
     * @return RefreshLayout
     */
    RefreshLayout setOnMultiListener(OnMultiListener listener);

    /**
     * Set the scroll boundary Decider, Can customize when you can refresh.
     * Recommended {@link com.orhotech.advanceswiperefreshlayout.simple.SimpleBoundaryDecider}
     * Set the scroll boundary judger
     * It is recommended to use {@link com.orhotech.advanceswiperefreshlayout.simple.SimpleBoundaryDecider}
     * @param boundary ScrollBoundaryDecider judger
     * @return RefreshLayout
     */
    RefreshLayout setScrollBoundaryDecider(ScrollBoundaryDecider boundary);

    /**
     * Set theme color int (primaryColor and accentColor).
     * Set theme color
     * @param primaryColors ColorInt theme color
     * @return RefreshLayout
     */
    RefreshLayout setPrimaryColors(@ColorInt int... primaryColors);

    /**
     * Set theme color id (primaryColor and accentColor).
     * Set theme color
     * @param primaryColorId ColorRes theme color ID
     * @return RefreshLayout
     */
    RefreshLayout setPrimaryColorsId(@ColorRes int... primaryColorId);

    /**
     * finish refresh.
     * Complete refresh
     * @return RefreshLayout
     */
    RefreshLayout finishRefresh();

    /**
     * finish refresh.
     * Complete refresh
     * @param delayed start delay
     * @return RefreshLayout
     */
    RefreshLayout finishRefresh(int delayed);

    /**
     * finish refresh.
     * Complete loading
     * @param success Whether the data is successfully refreshed (will affect the change of the last update time)
     * @return RefreshLayout
     */
    RefreshLayout finishRefresh(boolean success);

    /**
     * finish refresh.
     * Complete refresh
     * @param delayed start delay
     * @param success Whether the data is successfully refreshed (will affect the change of the last update time)
     * @param noMoreData whether there is more data
     * @return RefreshLayout
     */
    RefreshLayout finishRefresh(int delayed, boolean success, Boolean noMoreData);

    /**
     * finish load more with no more data.
     * Complete refresh and mark no more data
     * @return RefreshLayout
     */
    RefreshLayout finishRefreshWithNoMoreData();

    /**
     * finish load more.
     * Complete loading
     * @return RefreshLayout
     */
    RefreshLayout finishLoadMore();

    /**
     * finish load more.
     * Complete loading
     * @param delayed start delay
     * @return RefreshLayout
     */
    RefreshLayout finishLoadMore(int delayed);

    /**
     * finish load more.
     * Complete loading
     * @param success whether the data is successful
     * @return RefreshLayout
     */
    RefreshLayout finishLoadMore(boolean success);

    /**
     * finish load more.
     * Complete loading
     * @param delayed start delay
     * @param success whether the data is successful
     * @param noMoreData whether there is more data
     * @return RefreshLayout
     */
    RefreshLayout finishLoadMore(int delayed, boolean success, boolean noMoreData);

    /**
     * finish load more with no more data.
     * Finish loading and mark no more data
     * @return RefreshLayout
     */
    RefreshLayout finishLoadMoreWithNoMoreData();

    /**
     * Close the Header or Footer, can't replace finishRefresh and finishLoadMore.
     * Close Header or Footer
     * Notice:
     * 1.closeHeaderOrFooter can close header and footer at any time and in any state
     * 2.finishRefresh and finishLoadMore can only be closed when refreshing or loading
     * @return RefreshLayout
     */

    RefreshLayout closeHeaderOrFooter();

    /**
     * Restore the original state after finishLoadMoreWithNoMoreData.
     * Set the status of no more data
     * @param noMoreData whether there is more data
     * @return RefreshLayout
     * Try to use the following three methods instead. They can make the state switching and animation end coincide with each other.
     * use {@link RefreshLayout#resetNoMoreData()}
     * use {@link RefreshLayout#finishRefreshWithNoMoreData()}
     * use {@link RefreshLayout#finishLoadMoreWithNoMoreData()}
     */
    RefreshLayout setNoMoreData(boolean noMoreData);

    /**
     * Restore the original state after finishLoadMoreWithNoMoreData.
     * Restore original state without more data
     * @return RefreshLayout
     */
    RefreshLayout resetNoMoreData();

    /**
     * Get header of RefreshLayout
     * Get the current Header
     * @return RefreshLayout
     */
    @Nullable
    RefreshHeader getRefreshHeader();

    /**
     * Get footer of RefreshLayout
     * Get the current Footer
     * @return RefreshLayout
     */
    @Nullable
    RefreshFooter getRefreshFooter();

    /**
     * Get the current state of RefreshLayout
     * Get the current status
     * @return RefreshLayout
     */
    @NonNull
    RefreshState getState();

    /**
     * Get the ViewGroup of RefreshLayout
     * Get the entity layout view
     * @return ViewGroup
     */
    @NonNull
    ViewGroup getLayout();

    /**
     * Display refresh animation and trigger refresh event.
     * Display refresh animation and trigger refresh event
     * @return true or false, Status non-compliance will fail.
     * Whether it is successful (if the status does not match, it will fail)
     */
    boolean autoRefresh();

    /**
     * Display refresh animation and trigger refresh event, Delayed start.
     * Display refresh animation and trigger refresh event, delayed start
     * @param delayed start delay
     * @return true or false, Status non-compliance will fail.
     * Whether it is successful (if the status does not match, it will fail)
     */
    boolean autoRefresh(int delayed);

    /**
     * Display refresh animation without triggering events.
     * Display refresh animation, do not trigger events
     * @return true or false, Status non-compliance will fail.
     * Whether it is successful (if the status does not match, it will fail)
     */
    boolean autoRefreshAnimationOnly();

    /**
     * Display refresh animation, Multifunction.
     * Display refresh animation and trigger refresh event
     * @param delayed start delay
     * @param duration drag animation duration
     * @param dragRate drag height ratio
     * @param animationOnly animation only only animation
     * @return true or false, Status non-compliance will fail.
     * Whether it is successful (if the status does not match, it will fail)
     */
    boolean autoRefresh(int delayed, int duration, float dragRate, boolean animationOnly);

    /**
     * Display load more animation and trigger load more event.
     * Display loading animation and trigger refresh event
     * @return true or false, Status non-compliance will fail.
     * Whether it is successful (if the status does not match, it will fail)
     */
    boolean autoLoadMore();

    /**
     * Display load more animation and trigger load more event, Delayed start.
     * Display loading animation and trigger refresh event, delayed start
     * @param delayed start delay
     * @return true or false, Status non-compliance will fail.
     * Whether it is successful (if the status does not match, it will fail)
     */
    boolean autoLoadMore(int delayed);

    /**
     * Display load more animation without triggering events.
     * Show loading animation, do not trigger events
     * @return true or false, Status non-compliance will fail.
     * Whether it is successful (if the status does not match, it will fail)
     */
    boolean autoLoadMoreAnimationOnly();

    /**
     * Display load more animation and trigger load more event, Delayed start.
     * Show loading animation, multi-function options
     * @param delayed start delay
     * @param duration drag animation duration
     * @param dragRate drag height ratio
     * @param animationOnly Whether to just display animation without callback
     * @return true or false, Status non-compliance will fail.
     * Whether it is successful (if the status does not match, it will fail)
     */
    boolean autoLoadMore(int delayed, int duration, float dragRate, boolean animationOnly);

    /**
     * Whether it is refreshing
     * @return RefreshLayout
     */
    boolean isRefreshing();

    /**
     * Whether it is loading
     * @return RefreshLayout
     */
    boolean isLoading();

}
