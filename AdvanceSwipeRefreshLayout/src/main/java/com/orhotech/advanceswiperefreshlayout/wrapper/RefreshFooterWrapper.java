package com.orhotech.advanceswiperefreshlayout.wrapper;

import android.annotation.SuppressLint;
import android.view.View;

import com.orhotech.advanceswiperefreshlayout.api.RefreshFooter;
import com.orhotech.advanceswiperefreshlayout.simple.SimpleComponent;

/**
 * Refresh bottom wrapper
 * Created by rohit on 2024/10/02.
 */
@SuppressLint("ViewConstructor")
public class RefreshFooterWrapper extends SimpleComponent implements RefreshFooter {

    public RefreshFooterWrapper(View wrapper) {
        super(wrapper);
    }

}
