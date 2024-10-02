package com.orhotech.advanceswiperefreshlayout.listener;

import android.content.Context;

import androidx.annotation.NonNull;

import com.orhotech.advanceswiperefreshlayout.api.RefreshLayout;

/**
 * Default global initializer
 * Created by rohit on 2024/10/02.
 */
public interface DefaultRefreshInitializer {
    void initialize(@NonNull Context context, @NonNull RefreshLayout layout);
}
