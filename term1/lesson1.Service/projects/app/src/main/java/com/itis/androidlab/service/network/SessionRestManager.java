package com.itis.androidlab.service.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.itis.androidlab.service.BuildConfig;
import com.itis.androidlab.service.utils.AndroidUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SessionRestManager {

  private static volatile SessionRestManager instance;

  private SessionRestManager() {
  }

  public static SessionRestManager getInstance() {
    if (instance == null) {
      synchronized (SessionRestManager.class) {
        if (instance == null) instance = new SessionRestManager();
      }
    }
    return instance;
  }

  private OkHttpClient setupHttpClient() {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.addInterceptor(chain -> {
      Request original = chain.request();

      Request request = original.newBuilder()
          .header("Content-Type", "application/json")
          .header("Accept-Encoding", "identity")
          .method(original.method(), original.body())
          .build();

      return chain.proceed(request);
    });

    httpClient.addInterceptor(new AuthenticationInterceptor());

    // Задаём "уровень" логирования запросов
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    // set your desired log level
    loggingInterceptor.setLevel(
        BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    httpClient.addInterceptor(loggingInterceptor);

    if (BuildConfig.DEBUG) httpClient.networkInterceptors().add(new StethoInterceptor());

    return httpClient.build();
  }

  private final Retrofit REST_ADAPTER =
      new Retrofit.Builder().baseUrl(AndroidUtils.getRestEndpoint())
          .client(setupHttpClient())
          .addConverterFactory(JacksonConverterFactory.create())
          .build();

  public WeatherRest getRest() {
    return REST_ADAPTER.create(WeatherRest.class);
  }

  public Retrofit getRestAdapter() {
    return REST_ADAPTER;
  }
}
