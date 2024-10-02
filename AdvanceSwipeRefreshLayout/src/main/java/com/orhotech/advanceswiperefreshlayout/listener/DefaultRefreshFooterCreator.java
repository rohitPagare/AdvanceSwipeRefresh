package com.orhotech.advanceswiperefreshlayout.listener;

import android.content.Context;

import androidx.annotation.NonNull;

import com.orhotech.advanceswiperefreshlayout.api.RefreshFooter;
import com.orhotech.advanceswiperefreshlayout.api.RefreshLayout;

/**
 * Default Footer creator
 * Created by rohit on 2024/10/02.
 */
public interface DefaultRefreshFooterCreator {
    @NonNull
    RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout);
}
