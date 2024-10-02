package com.orhotech.advanceswiperefreshlayout.listener;

import android.content.Context;

import androidx.annotation.NonNull;

import com.orhotech.advanceswiperefreshlayout.api.RefreshHeader;
import com.orhotech.advanceswiperefreshlayout.api.RefreshLayout;

/**
 * Default Header Creator
 * Created by rohit on 2024/10/02.
 */
public interface DefaultRefreshHeaderCreator {
    @NonNull
    RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout);
}
