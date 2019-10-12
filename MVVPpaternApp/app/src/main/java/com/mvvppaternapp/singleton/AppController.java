package com.mvvppaternapp.singleton;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.mvvppaternapp.apipreesenter.ApiConstants;
import com.mvvppaternapp.apipreesenter.RestApi;
import com.mvvppaternapp.utils.CommonUtils;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class AppController extends MultiDexApplication {

    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;
    public static Context context;
    public RestApi service;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = this;

        Stetho.initializeWithDefaults(this);
        OkHttpClient okHttpClient = createCachedClient(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(getRequestHeader())
                //  .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(RestApi.class);


    }

    private OkHttpClient createCachedClient(final Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "cache_file");

        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);//10 mb
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(cache);
        okHttpClient.interceptors().add(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        String cacheHeaderValue = CommonUtils.isNetworkAvailable(context)
                                ? "public, max-age=60*60*24"                         //? "public, max-age=2419200"
                                : "public, only-if-cached, max-stale=60*60*24";   //: "public, only-if-cached, max-stale=2419200" ;
                        Request request = originalRequest.newBuilder().build();
                        Response response = chain.proceed(request);
                        return response.newBuilder()
                                .removeHeader("Pragma")
                                .removeHeader("Cache-Control")
                                .header("Cache-Control", cacheHeaderValue)
                                .build();
                    }
                }
        );
        okHttpClient.networkInterceptors().add(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        String cacheHeaderValue = CommonUtils.isNetworkAvailable(context)

                                ? "public, max-age=60*60*24"
                                : "public, only-if-cached, max-stale=60*60*24";

                             /*   ? "public, max-age=2419200"
                                : "public, only-if-cached, max-stale=2419200";*/

                        Request request = originalRequest.newBuilder().build();
                        Response response = chain.proceed(request);
                        return response.newBuilder()
                                .removeHeader("Pragma")
                                .removeHeader("Cache-Control")
                                .header("Cache-Control", cacheHeaderValue)
                                .build();
                    }
                }
        );
        return okHttpClient;
    }


    private OkHttpClient getRequestHeader() {
      /*  File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);*/

/*
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setCache(cache);
        httpClient.networkInterceptors().add(REWRITE_RESPONSE_INTERCEPTOR);
        httpClient.interceptors().add(OFFLINE_INTERCEPTOR);
        httpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        httpClient.setReadTimeout(30, TimeUnit.SECONDS);
        return httpClient;*/


        //changed client for sthetho
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setConnectTimeout(60, TimeUnit.SECONDS);
        httpClient.setReadTimeout(30, TimeUnit.SECONDS);
        httpClient.networkInterceptors().add(new StethoInterceptor());
        return httpClient;
    }

    private static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + 60)
                        .build();
            } else {
                return originalResponse;
            }
        }
    };

    private static final Interceptor OFFLINE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!CommonUtils.isNetworkAvailable(context)) {
                request = request.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached")
                        .build();
            }
            return chain.proceed(request);
        }
    };


    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;

    }
