package com.mhksoft.novineiran;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by MHK on 2019-12-13.
 * www.MHKSoft.com
 */
public class App extends Application {
    private static Retrofit retrofit = null;
    private static String baseUrl = "https://my-json-server.typicode.com/MHKalantarian/NovineIran/";

    /**
     * Retrofit Singleton
     *
     * @param mContext Activity context
     * @return Retrofit instance
     */
    public static Retrofit getRetrofit(Context mContext) {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.level(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
            httpClient.connectTimeout(60, TimeUnit.SECONDS);
            httpClient.readTimeout(60, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

}
