package com.mhksoft.novineiran.data.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mhksoft.novineiran.R;
import com.mhksoft.novineiran.data.model.Category;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by MHK on 2019-12-15.
 * www.MHKSoft.com
 */
public class CategoryAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {
    private NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("fa", "IR"));

    public CategoryAdapter() {
        super(R.layout.category_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, Category item) {
        Glide.with(getContext())
                .load("https://source.unsplash.com/512x256/?" + item.getTag())
                .centerCrop()
                .signature(new ObjectKey(item.getName()))
                .placeholder(R.drawable.ic_loading_placeholder)
                .into((ImageView) helper.getView(R.id.thumbnail_iv));

        helper.setText(R.id.name_tv, item.getName())
                .setText(R.id.itemsCount_tv, item.getCourses().size() + " دوره");
    }
}
