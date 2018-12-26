package io.johan.roommvp.data.contract;

import io.johan.roommvp.data.entity.PokeList;
import java.util.List;

public interface MainActivityContract {

  interface View {
    void showLoadingBar();
    void removeLoadingBar();
    void onSuccess(List<PokeList> lists);
    void onError(String errorMessage);
  }

  interface Presenter {
    void getList();

    void onSuccess(List<PokeList> pokeLists);
    void onError(Throwable error);
  }

}
