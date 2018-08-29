package com.festivality.conferenceapp.data.providers;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.festivality.conferenceapp.BuildConfig;
import com.festivality.conferenceapp.app.Constants;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.remote.converters.AttendeeResponseDeserializer;
import com.festivality.conferenceapp.data.remote.interceptors.ContentTypeInterceptor;
import com.festivality.conferenceapp.helper.tls.Tls12SocketFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceFactory {
    public interface UserTokenProducer {
        String getUserToken();
    }

    public static <T> T makeService(Class<T> serviceClass, Gson gson, OkHttpClient okHttpClient) {
        Gson attendeeDeserializer = new GsonBuilder().setLenient().registerTypeAdapter(Attendee.class, new AttendeeResponseDeserializer()).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                //.addConverterFactory(new ResponseConverter(gson))
                .addConverterFactory(GsonConverterFactory.create(attendeeDeserializer))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        return retrofit.create(serviceClass);
    }

    public static OkHttpClient.Builder makeOkHttpClientBuilder(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        OkHttpClient.Builder builder;
        builder = new OkHttpClient.Builder()
                //.addInterceptor(new AuthorizationInterceptor(producer))
                //.addInterceptor(new PaginationInterceptor())
                .addInterceptor(new ContentTypeInterceptor())
                .addInterceptor(logging)
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .cache(null)
                .connectTimeout(Constants.API_CONSTANTS.TIME_OUT_API, TimeUnit.SECONDS)
                .writeTimeout(Constants.API_CONSTANTS.TIME_OUT_API, TimeUnit.SECONDS)
                .readTimeout(Constants.API_CONSTANTS.TIME_OUT_API, TimeUnit.SECONDS);

        // add cache to client
        final File baseDir = context.getCacheDir();
        if (baseDir != null) {
            final File cacheDir = new File(baseDir, "HttpResponseCache");
            builder.cache(new Cache(cacheDir, 10 * 1024 * 1024)); // 10 MB
        }

        return enableTls12OnPreLollipop(builder);
    }

    /**
     * Enable TLS 1.2 on Pre Lollipop android versions
     *
     * @param client OkHttpClient Builder
     * @return builder with SSL Socket Factory set
     * according to {@link OkHttpClient.Builder#sslSocketFactory(SSLSocketFactory)} deprecation,
     * Please add config SSL with {@link X509TrustManager} by using {@link com.festivality.conferenceapp.helper.tls.CustomTrustManager}
     * * how to enable tls on android 4.4
     * <a href="https://github.com/square/okhttp/issues/2372"></a>
     */
    private static OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder client) {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 22) {
            try {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, null, null);
                // TODO: 9/7/17 set SSL socket factory with X509TrustManager
                client.sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()));

                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();

                List<ConnectionSpec> specs = new ArrayList<>();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);

                client.connectionSpecs(specs);
            } catch (Exception exc) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
            }
        }

        return client;
    }

    /*@NonNull
    public static UserRestService getContributionService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.REST_URL)
                .addConverterFactory(new ResponseConverter(ExpressApplication.getInstance().getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(UserRestService.class);
    }*/
}
