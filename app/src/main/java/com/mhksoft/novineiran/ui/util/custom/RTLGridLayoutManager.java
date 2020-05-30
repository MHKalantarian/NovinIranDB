package com.mhksoft.novineiran.ui.util.custom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;


public class RTLGridLayoutManager extends GridLayoutManager {
    public RTLGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public RTLGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public RTLGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    protected boolean isLayoutRTL() {
        return true;
    }
}
