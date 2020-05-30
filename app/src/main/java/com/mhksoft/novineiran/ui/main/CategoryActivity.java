package com.mhksoft.novineiran.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.mhksoft.novineiran.R;
import com.mhksoft.novineiran.data.adapter.CourseAdapter;
import com.mhksoft.novineiran.data.model.Category;
import com.mhksoft.novineiran.ui.util.BaseActivity;
import com.mhksoft.novineiran.ui.util.custom.GridAutofitLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.list_rv)
    RecyclerView listRv;
    @BindView(R.id.parent)
    LinearLayout parent;
    private List<Category> categories;
    private CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        categories = getIntent().getParcelableArrayListExtra("Categories");
        initUI();
        initData();
    }

    private void initUI() {
        setupParentView(parent);
        setupToolbar(toolbar, true);
        listRv.setLayoutManager(new GridAutofitLayoutManager(mContext, getResources().getDimensionPixelSize(R.dimen._128sdp), LinearLayoutManager.VERTICAL, false));
        adapter = new CourseAdapter();
        adapter.setAnimationFirstOnly(false);
        adapter.setAdapterAnimation(new ScaleInAnimation());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> a, @NonNull View view, int position) {
                Intent intent = new Intent(mContext, CourseActivity.class);
                intent.putExtra("Course", adapter.getItem(position));

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(CategoryActivity.this,
                        new Pair<View, String>(adapter.getViewByPosition(position, R.id.thumbnail_iv), ViewCompat.getTransitionName(adapter.getViewByPosition(position, R.id.thumbnail_iv))));

                ActivityCompat.startActivity(mContext, intent, options.toBundle());
            }
        });
        listRv.setAdapter(adapter);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                adapter.setNewInstance(categories.get(tab.getPosition()).getCourses());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initData() {
        showLoadingDialog();

        for (Category category : categories) {
            tabs.addTab(tabs.newTab().setText(category.getName()));
        }
        scrollToTabAfterLayout(getIntent().getIntExtra("CategoryPosition", 0));
        hideLoadingDialog();
    }

    private void scrollToTabAfterLayout(final int tabIndex) {
        final ViewTreeObserver observer = tabs.getViewTreeObserver();

        if (observer.isAlive()) {
            observer.dispatchOnGlobalLayout();
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    tabs.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    tabs.getTabAt(tabIndex).select();
                }
            });
        }
    }
}