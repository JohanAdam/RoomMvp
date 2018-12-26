package io.johan.roommvp.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.johan.roommvp.contants.Constants;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceManager {

  private static Retrofit retrofit = null;
  private static ServiceManager serviceManager;

  private ApiInterface apiInterface = null;

  private ServiceManager() {
    if (retrofit == null) {

      OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
      httpClient.addInterceptor(chain -> {
        Request original = chain.request();
        Request request = original.newBuilder().build();
        return chain.proceed(request);
      })
          .connectTimeout(100, TimeUnit.SECONDS)
          .writeTimeout(100, TimeUnit.SECONDS)
          .readTimeout(100, TimeUnit.SECONDS);

      OkHttpClient client = httpClient.build();

      retrofit = new Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .client(client)
          .addConverterFactory(GsonConverterFactory.create(provideGson()))
          .build();

      apiInterface = retrofit.create(ApiInterface.class);
    }
  }

  private Gson provideGson() {
    GsonBuilder builder = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    return builder.setLenient().create();
  }

  public static ServiceManager getInstance() {
    if (serviceManager == null) {
      serviceManager = new ServiceManager();
    }
    return serviceManager;
  }

  public ApiInterface getApiInterface() {
    return apiInterface;
  }

}
