package com.mhksoft.novineiran.data.binder;

import com.mhksoft.novineiran.data.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by MHK on 2020-03-25.
 * www.MHKSoft.com
 */
public interface ApiService {
    // Application
    @GET("categories")
    Call<List<Category>> getCategories();
}
