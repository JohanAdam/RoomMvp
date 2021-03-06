package io.johan.roommvp.data.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import io.johan.roommvp.data.dao.PokeListDao;
import io.johan.roommvp.data.entity.PokeList;

@Database(entities = {PokeList.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

  private static AppDatabase INSTANCE;

  public abstract PokeListDao pokeListDao();

  public static AppDatabase getAppDatabase(Context context) {
    if (INSTANCE == null) {
      INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "poke-database")
          // allow queries on the main thread.
          // Dont do this on a real app! See PersistanceBasicSample for an example.
          .allowMainThreadQueries()
          .build();
    }
    return INSTANCE;
  }

  public static void destroyInstance() {
    INSTANCE = null;
  }

  @NonNull
  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
    return null;
  }

  @NonNull
  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return null;
  }
}
