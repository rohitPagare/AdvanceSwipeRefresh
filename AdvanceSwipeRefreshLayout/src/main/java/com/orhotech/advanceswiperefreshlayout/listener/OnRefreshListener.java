package com.orhotech.advanceswiperefreshlayout.listener;

import androidx.annotation.NonNull;

import com.orhotech.advanceswiperefreshlayout.api.RefreshLayout;

/**
 * Refresh listener
 * Created by rohit on 2024/10/02.
 */
public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
