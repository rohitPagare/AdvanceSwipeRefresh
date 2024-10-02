package com.orhotech.advanceswiperefreshlayout.wrapper;

import android.annotation.SuppressLint;
import android.view.View;

import com.orhotech.advanceswiperefreshlayout.api.RefreshHeader;
import com.orhotech.advanceswiperefreshlayout.simple.SimpleComponent;

/**
 * Refresh header packaging
 * Created by rohit on 2024/10/02.
 */
@SuppressLint("ViewConstructor")
public class RefreshHeaderWrapper extends SimpleComponent implements RefreshHeader {

    public RefreshHeaderWrapper(View wrapper) {
        super(wrapper);
    }

}
