package io.johan.roommvp.services;

import io.johan.roommvp.App;
import io.johan.roommvp.contants.NetworkResponseHandler;
import io.johan.roommvp.models.Pokemon;
import io.johan.roommvp.data.database.AppDatabase;
import io.johan.roommvp.data.entity.PokeList;
import io.johan.roommvp.presenter.MainActivityPresenter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

public class MainService implements NetworkResponseHandler {

  MainActivityPresenter presenter;
  NetworkRequestManager networkRequestManager;

  public MainService(MainActivityPresenter mainActivityPresenter) {
    this.presenter = mainActivityPresenter;
    this.networkRequestManager = new NetworkRequestManager(this);
  }

  public void getList() {
    Timber.d("getList");

    List<PokeList> listLocal = getLocalList();
    if (listLocal.size() == 0) {
      Timber.d("getList online because local list is 0");
      networkRequestManager.getList();
    } else {
      Timber.d("getList local because there is local list");
      presenter.onSuccess(listLocal);
    }
  }

  @Override
  public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
//  public void onResponse(Call<List<PokeList>> call, Response<List<PokeList>> response) {
    //handle success response here
    Timber.d("onResponse %s" , response.body().getCount());
    saveLists(response.body().getResults());
    presenter.onSuccess(response.body().getResults());
  }

  private List<PokeList> getLocalList() {
    Timber.d("getList local");
    AppDatabase appDatabase = AppDatabase.getAppDatabase(App.getContext());
    return appDatabase.pokeListDao().getAll();
  }

  private void saveLists(List<PokeList> body) {
    Timber.d("saveLists");
    AppDatabase appDatabase = AppDatabase.getAppDatabase(App.getContext());
    appDatabase.pokeListDao().insertAll(body);
  }

  public void updateItem(String oldName, String newName) {
    Timber.d("updateItem oldName %s", oldName);
    Timber.d("updateItem newName %s", newName);
    AppDatabase appDatabase = AppDatabase.getAppDatabase(App.getContext());
    appDatabase.pokeListDao().updateItem(oldName, newName);
  }

  @Override
  public void onFailure(Call<Pokemon> call, Throwable t) {
//  public void onFailure(Call<List<PokeList>> call, Throwable t) {
    Timber.e("onFailure");
    t.printStackTrace();
    presenter.onError(t);
  }

}
