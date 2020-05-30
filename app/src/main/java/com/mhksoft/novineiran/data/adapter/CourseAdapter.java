package com.mhksoft.novineiran.data.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mhksoft.novineiran.R;
import com.mhksoft.novineiran.data.model.Course;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by MHK on 2019-12-15.
 * www.MHKSoft.com
 */
public class CourseAdapter extends BaseQuickAdapter<Course, BaseViewHolder> {
    private NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("fa", "IR"));

    public CourseAdapter() {
        super(R.layout.course_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, Course item) {
        Glide.with(getContext())
                .load("https://source.unsplash.com/256x256/?" + item.getTag())
                .centerCrop()
                .signature(new ObjectKey(item.getName()))
                .placeholder(R.drawable.ic_loading_placeholder)
                .into((ImageView) helper.getView(R.id.thumbnail_iv));

        helper.setText(R.id.name_tv, item.getName())
                .setText(R.id.price_tv, currencyFormat.format(item.getPrice()) + " تومان");
    }
}
