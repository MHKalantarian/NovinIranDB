package com.mhksoft.novineiran.ui.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.mhksoft.novineiran.R;
import com.mhksoft.novineiran.data.model.Course;
import com.mhksoft.novineiran.ui.util.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;
    @BindView(R.id.thumbnail_iv)
    ImageView thumbnailIv;
    @BindView(R.id.name_tv)
    MaterialTextView nameTv;
    @BindView(R.id.description_tv)
    MaterialTextView descriptionTv;
    @BindView(R.id.price_tv)
    MaterialTextView priceTv;
    @BindView(R.id.buy_btn)
    ExtendedFloatingActionButton buyBtn;
    @BindView(R.id.parent)
    LinearLayout parent;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ButterKnife.bind(this);
        course = getIntent().getParcelableExtra("Course");
        initUI();
    }

    private void initUI() {
        setupParentView(parent);
        setupToolbar(toolbar, true);
        supportPostponeEnterTransition();

        Glide.with(this)
                .load("https://source.unsplash.com/256x256/?" + course.getTag())
                .centerCrop()
                .dontAnimate()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        return false;
                    }
                })
                .signature(new ObjectKey(course.getName()))
                .into(thumbnailIv);
        nameTv.setText(course.getName());
        priceTv.setText(currencyFormat.format(course.getPrice()) + " تومان");
        descriptionTv.setMovementMethod(new ScrollingMovementMethod());
    }
}