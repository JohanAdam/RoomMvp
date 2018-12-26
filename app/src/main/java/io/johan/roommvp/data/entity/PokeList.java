package io.johan.roommvp.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Entity
 *
 * It represents data for a single table row. Room provide
 * construction of entity using annotations.
 */

@Entity(tableName = "pokemon")
public class PokeList {
  @PrimaryKey
  @NonNull
  String name;

  @ColumnInfo(name = "profile_url")
  String url;

  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
