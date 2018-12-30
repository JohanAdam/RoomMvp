package io.johan.roommvp.data.contract;

import io.johan.roommvp.data.entity.PokeList;
import java.util.List;

public interface MainActivityContract {

  interface View {
    void showLoadingBar();
    void removeLoadingBar();
    void setItemInAdapter(int position, PokeList pokeList);
    void onSuccess(List<PokeList> lists);
    void onError(String errorMessage);
  }

  interface Presenter {
    void getList();
    void update(int positionItem, PokeList oldName, PokeList newName);
    void onSuccess(List<PokeList> pokeLists);
    void onError(Throwable error);
  }

}
