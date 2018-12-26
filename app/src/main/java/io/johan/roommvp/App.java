package io.johan.roommvp;

import android.app.Application;
import android.content.Context;
import timber.log.Timber;

public class App extends Application {

  private static Context context;

  @Override
  public void onCreate() {
    super.onCreate();
    Timber.plant(new Timber.DebugTree());

    context = getApplicationContext();
  }

  public static Context getContext() {
    return context;
  }
}
