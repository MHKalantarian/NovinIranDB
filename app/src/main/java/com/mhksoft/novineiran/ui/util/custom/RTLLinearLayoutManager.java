package com.mhksoft.novineiran.ui.util.custom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;


public class RTLLinearLayoutManager extends LinearLayoutManager {
    public RTLLinearLayoutManager(Context context) {
        super(context);
    }

    public RTLLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public RTLLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public RTLLinearLayoutManager(Context context, int orientation, boolean reverseLayout, boolean stackFromEnd) {
        super(context, orientation, reverseLayout);
        this.setStackFromEnd(stackFromEnd);
    }

    @Override
    protected boolean isLayoutRTL() {
        return true;
    }
}
