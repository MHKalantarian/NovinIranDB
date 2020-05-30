package com.mhksoft.novineiran.ui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.labters.lottiealertdialoglibrary.ClickListener;
import com.labters.lottiealertdialoglibrary.DialogTypes;
import com.labters.lottiealertdialoglibrary.LottieAlertDialog;
import com.mhksoft.novineiran.App;
import com.mhksoft.novineiran.R;
import com.mhksoft.novineiran.data.binder.ApiService;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by MHK on 2019-12-13.
 * www.MHKSoft.com
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    public ApiService api;
    public MessageHandler messageHandler;
    protected Context mContext;
    protected NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("fa", "IR"));
    private LottieAlertDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mContext = this;
        api = App.getRetrofit(mContext).create(ApiService.class);

        // Progress dialog setup
        mProgressDialog = new LottieAlertDialog.Builder(this, DialogTypes.TYPE_LOADING, "loading.json")
                .setTitle("صبور باشید...")
                .setDescription("در حال ارسال و دریافت اطلاعات")
                .build();
        mProgressDialog.setCancelable(false);
    }

    protected boolean isFormValid(ViewGroup form) {
        return validateForm(form) == 0;
    }

    private int validateForm(ViewGroup form) {
        int errorCount = 0;

        for (int i = 0; i < form.getChildCount(); i++) {
            View child = form.getChildAt(i);
            if (child instanceof TextInputEditText) {
                if (child.getTag() == null && child.getVisibility() == View.VISIBLE) {
                    if (TextUtils.isEmpty(((TextInputEditText) child).getText())) {
                        ((TextInputEditText) child).setError("این فیلد الزامی است");
                        errorCount++;
                    }
                } else if (!child.getTag().equals("optional")) {
                    if (TextUtils.isEmpty(((TextInputEditText) child).getText())) {
                        ((TextInputEditText) child).setError("این فیلد الزامی است");
                        errorCount++;
                    } else if (((TextInputEditText) child).getText().length() < Integer.parseInt(child.getTag().toString())) {
                        ((TextInputEditText) child).setError("این فیلد نامعتبر است");
                        errorCount++;
                    }
                }
            } else if (child instanceof ViewGroup)
                errorCount += validateForm((ViewGroup) child);
        }

        return errorCount;
    }

    protected void setupToolbar(Toolbar toolbar, boolean withBackNavigation) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(withBackNavigation);
    }

    protected void setupParentView(View parentView) {
        messageHandler = new MessageHandler(mContext, parentView);
    }

    /**
     * Handles the back button on toolbar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Hides soft keyboard
     */
    protected void hideKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows progress dialog
     */
    public void showLoadingDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    /**
     * Hides already shown progress dialog
     */
    public void hideLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    protected void changeLoadingDialog(String title, String text, String animationAsset) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.changeDialog(new LottieAlertDialog.Builder(mContext, DialogTypes.TYPE_CUSTOM, animationAsset)
                    .setTitle(title)
                    .setDescription(text)
                    .setNoneText("باشه")
                    .setNoneTextColor(getResources().getColor(R.color.surface_light))
                    .setNoneListener(new ClickListener() {
                        @Override
                        public void onClick(@NotNull LottieAlertDialog lottieAlertDialog) {
                            lottieAlertDialog.dismiss();
                        }
                    }));
    }

    protected LottieAlertDialog getLoadingDialog() {
        return mProgressDialog;
    }
}
