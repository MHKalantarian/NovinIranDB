package com.mhksoft.novineiran.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.AlphaInAnimation;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.mhksoft.novineiran.R;
import com.mhksoft.novineiran.data.adapter.CategoryAdapter;
import com.mhksoft.novineiran.data.model.Category;
import com.mhksoft.novineiran.ui.util.BaseActivity;
import com.mhksoft.novineiran.ui.util.custom.RTLLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;
    @BindView(R.id.list_rv)
    RecyclerView listRv;
    @BindView(R.id.swipeRefresh_l)
    SwipeRefreshLayout swipeRefreshL;
    @BindView(R.id.parent)
    LinearLayout parent;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
        initData();
    }

    private void initUI() {
        setupParentView(parent);
        setupToolbar(toolbar, false);

        listRv.setLayoutManager(new RTLLinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        adapter = new CategoryAdapter();
        adapter.setAnimationFirstOnly(false);
        adapter.setAdapterAnimation(new AlphaInAnimation());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> a, @NonNull View view, int position) {
                startActivity(
                        new Intent(mContext, CategoryActivity.class)
                                .putParcelableArrayListExtra("Categories", new ArrayList<>(adapter.getData()))
                                .putExtra("CategoryPosition", position)
                );
            }
        });
        listRv.setAdapter(adapter);

        swipeRefreshL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    private void initData() {
        if (swipeRefreshL.isRefreshing())
            swipeRefreshL.setRefreshing(false);
        showLoadingDialog();
        api.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                hideLoadingDialog();
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        adapter.setNewInstance(response.body());
                    } else {
                        messageHandler.throwNoDataWarning();
                        finish();
                    }
                } else {
                    messageHandler.throwInternalServerError();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                hideLoadingDialog();
                messageHandler.throwInternetConnectionError(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initData();
                    }
                });
            }
        });
    }
}
