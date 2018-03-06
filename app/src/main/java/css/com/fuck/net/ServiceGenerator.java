package css.com.fuck.net;


import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import css.com.fuck.app.AppManager;
import css.com.fuck.utils.NetWorkUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class ServiceGenerator {
    /**
     * 缓存时间 7天
     */
    private static final int CACHE_TIME = 60 * 60 * 24 * 7;
    /**
     * 设置网络请求超时时间
     */
    private static final int TIME_OUT = 10;

    private static final String BASE_URL = "http://120.24.55.58:8083/index.php/";
    private static volatile OkHttpClient okHttpClient;
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL);
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .addConverterFactory(MoshiConverterFactory.create());

    private static Retrofit.Builder retrofitBuilderTest =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL);
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .addConverterFactory(ScalarsConverterFactory.create());

    private static OkHttpClient.Builder okHttpClientBuilder =
            new OkHttpClient.Builder();

    public static <T> T createService(Class<T> serviceClass) {
        retrofitBuilder.client(getOkHttpClient());
        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    public static <T> T createServiceTest(Class<T> serviceClass) {
        retrofitBuilderTest.client(okHttpClientBuilder.build());
        Retrofit retrofit = retrofitBuilderTest.build();
        return retrofit.create(serviceClass);
    }

    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (ServiceGenerator.class) {
                Cache cache = new Cache(new File(AppManager.getInstance().getCacheDir(), "HttpCache"),
                        1024 * 1024 * 100);
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(interceptor)
                            .addInterceptor(loggingInterceptor)
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * 配置缓存策略
     * 有网络读服务器最新数据，没网读缓存
     */
    private static final Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetworkAvailable()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (NetWorkUtils.isNetworkAvailable()) {
                response.newBuilder().header("Cache-Control", "public, max-age=" + 0)
                        .removeHeader("Pragma")
                        .build();
            } else {
                response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_TIME)
                        .removeHeader("Pragma")
                        .build();
            }

            return response;
        }
    };
    private static final Interceptor loggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.d("",String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.d("", String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };
}