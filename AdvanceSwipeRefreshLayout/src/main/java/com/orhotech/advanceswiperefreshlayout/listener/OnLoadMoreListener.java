package com.orhotech.advanceswiperefreshlayout.listener;

import androidx.annotation.NonNull;

import com.orhotech.advanceswiperefreshlayout.api.RefreshLayout;

/**
 * Load more listeners
 * Created by rohit on 2024/10/02.
 */
public interface OnLoadMoreListener {
    void onLoadMore(@NonNull RefreshLayout refreshLayout);
}
