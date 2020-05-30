package com.mhksoft.novineiran.ui.util;

import android.content.Context;
import android.view.View;

import androidx.core.view.ViewCompat;

import com.google.android.material.snackbar.Snackbar;
import com.mhksoft.novineiran.R;

/**
 * Created by MHK on 5/13/2017.
 * www.MHKSoft.com
 */

public class MessageHandler {
    private Context mContext;
    private View parentView;
    private Snackbar snackbar;

    public MessageHandler(Context mContext, View parentView) {
        this.mContext = mContext;
        this.parentView = parentView;
    }

    public void throwInternetConnectionError(View.OnClickListener action) {
        snackbar = Snackbar.make(parentView, mContext.getResources().getString(R.string.internet_error), Snackbar.LENGTH_INDEFINITE).setAction("تلاش مجدد", action);
        show();
    }

    public void throwInternetConnectionError() {
        snackbar = Snackbar.make(parentView, mContext.getResources().getString(R.string.internet_error), Snackbar.LENGTH_LONG);
        show();
    }

    public void throwInternalServerError() {
        snackbar = Snackbar.make(parentView, mContext.getResources().getString(R.string.server_error), Snackbar.LENGTH_LONG);
        show();
    }

    public void throwInternalServerError(View.OnClickListener action) {
        snackbar = Snackbar.make(parentView, mContext.getResources().getString(R.string.server_error), Snackbar.LENGTH_INDEFINITE).setAction("تلاش مجدد", action);
        show();
    }

    public void throwEmptyFieldWarning() {
        snackbar = Snackbar.make(parentView, mContext.getResources().getString(R.string.empty_field_warning), Snackbar.LENGTH_LONG);
        show();
    }

    public void throwNotValidFieldWarning() {
        snackbar = Snackbar.make(parentView, mContext.getResources().getString(R.string.not_valid_field_warning), Snackbar.LENGTH_LONG);
        show();
    }

    public void throwWrongInfo() {
        snackbar = Snackbar.make(parentView, mContext.getResources().getString(R.string.wrong_info_error), Snackbar.LENGTH_LONG);
        show();
    }

    public void throwNoDataWarning() {
        snackbar = Snackbar.make(parentView, mContext.getResources().getString(R.string.no_data_warning), Snackbar.LENGTH_LONG);
        show();
    }

    public void throwSuccessRequest() {
        snackbar = Snackbar.make(parentView, mContext.getResources().getString(R.string.success_request), Snackbar.LENGTH_LONG);
        show();
    }

    public void throwCustomMessage(String message) {
        snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG);
        show();
    }

    public void throwCustomMessageWithAction(String message, String actionText, View.OnClickListener action) {
        snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_INDEFINITE).setAction(actionText, action);
        show();
    }

    private void show() {
        dismissPrevious();
        ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
        snackbar.show();
    }

    private void dismissPrevious() {
        if (snackbar != null)
            snackbar.dismiss();
    }
}
