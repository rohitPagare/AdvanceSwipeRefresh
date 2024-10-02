package com.orhotech.advanceswiperefreshlayout.listener;


import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import com.orhotech.advanceswiperefreshlayout.api.RefreshLayout;
import com.orhotech.advanceswiperefreshlayout.constant.RefreshState;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;
import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static androidx.annotation.RestrictTo.Scope.SUBCLASSES;


/**
 * Refresh state change listener
 * Created by rohit on 2024/10/02.
 */
public interface OnStateChangedListener {
    /**
     * [Only called within the frame] State change event {@link RefreshState}
     * @param refreshLayout RefreshLayout
     * @param oldState Change the previous state
     * @param newState state after change
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState);
}
