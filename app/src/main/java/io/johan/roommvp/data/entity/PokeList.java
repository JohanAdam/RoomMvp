package io.johan.roommvp.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Entity
 *
 * It represents data for a single table row. Room provide
 * construction of entity using annotations.
 */

@Entity(tableName = "pokemon")
public class PokeList implements Parcelable {
  @PrimaryKey
  @NonNull
  String name;

  @ColumnInfo(name = "profile_url")
  String url;

  public PokeList() {

  }

  public PokeList(Parcel in) {
    name = in.readString();
    url = in.readString();
  }

  public static final Creator<PokeList> CREATOR = new Creator<PokeList>() {
    @Override
    public PokeList createFromParcel(Parcel in) {
      return new PokeList(in);
    }

    @Override
    public PokeList[] newArray(int size) {
      return new PokeList[size];
    }
  };

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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(url);
  }
}
