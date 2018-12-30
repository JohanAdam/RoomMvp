package io.johan.roommvp.presenter;

import io.johan.roommvp.services.MainService;
import io.johan.roommvp.data.contract.MainActivityContract.Presenter;
import io.johan.roommvp.data.contract.MainActivityContract.View;
import io.johan.roommvp.data.entity.PokeList;
import java.util.List;
import timber.log.Timber;

public class MainActivityPresenter implements Presenter {

  View view;
  private final MainService mainService;

  public MainActivityPresenter(View view) {
    this.view = view;
    this.mainService = new MainService(this);
  }

  @Override
  public void getList() {
    Timber.d("getList");
    view.showLoadingBar();
//    final Handler handler = new Handler();
//    handler.postDelayed(new Runnable() {
//      @Override
//      public void run() {
        mainService.getList();
//      }
//    }, 10000);
  }

  @Override
  public void update(int positionItem, PokeList oldName, PokeList newName) {
    Timber.d("update oldName " + oldName + " newName " + newName);
    mainService.updateItem(oldName.getName(), newName.getName());
    view.setItemInAdapter(positionItem, newName);
  }

  @Override
  public void onSuccess(List<PokeList> pokeLists) {
    Timber.d("onSuccess pokeList %s", pokeLists.size());
    view.removeLoadingBar();
    view.onSuccess(pokeLists);
  }

  @Override
  public void onError(Throwable error) {
    Timber.e("onError");
    view.removeLoadingBar();
    view.onError(error.getMessage());
  }
}
