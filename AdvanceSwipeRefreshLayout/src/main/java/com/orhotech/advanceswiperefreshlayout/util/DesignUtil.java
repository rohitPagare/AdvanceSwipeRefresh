package com.orhotech.advanceswiperefreshlayout.util;

import android.view.View;
import android.view.ViewGroup;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.orhotech.advanceswiperefreshlayout.api.RefreshKernel;
import com.orhotech.advanceswiperefreshlayout.listener.CoordinatorLayoutListener;

/**
 * Design Compatibility package default attempt
 * Created by rohit on 2024/10/02.
 */
public class DesignUtil {

    public static void checkCoordinatorLayout(View content, RefreshKernel kernel, final CoordinatorLayoutListener listener) {
        try {//try cannot be deleted, otherwise compatibility issues will occur
            if (content instanceof CoordinatorLayout) {
                kernel.getRefreshLayout().setEnableNestedScroll(false);
                ViewGroup layout = (ViewGroup) content;
                for (int i = layout.getChildCount() - 1; i >= 0; i--) {
                    View view = layout.getChildAt(i);
                    if (view instanceof AppBarLayout) {
                        ((AppBarLayout) view).addOnOffsetChangedListener((appBarLayout, verticalOffset) -> listener.onCoordinatorUpdate(
                                verticalOffset >= 0,
                                (appBarLayout.getTotalScrollRange() + verticalOffset) <= 0));
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
