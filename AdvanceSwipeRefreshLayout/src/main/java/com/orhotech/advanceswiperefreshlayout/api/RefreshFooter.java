package com.orhotech.advanceswiperefreshlayout.api;


import androidx.annotation.RestrictTo;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;
import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static androidx.annotation.RestrictTo.Scope.SUBCLASSES;

/**
 * refresh bottom
 * Created by rohit on 2024/10/02.
 */
public interface RefreshFooter extends RefreshComponent {

    /**
     * [Only called within the frame] After all the setting data has been loaded, the loading function cannot be triggered again.
     * @param noMoreData whether there is more data
     * @return true supports displaying the status of all loading completions false does not support
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    boolean setNoMoreData(boolean noMoreData);
}
