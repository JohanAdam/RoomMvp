package io.johan.roommvp.services;


import io.johan.roommvp.models.Pokemon;
import io.johan.roommvp.contants.NetworkResponseHandler;
import retrofit2.Call;
import timber.log.Timber;

public class NetworkRequestManager {

  private NetworkResponseHandler networkResponseHandler;

  public NetworkRequestManager(MainService mainService) {
    this.networkResponseHandler = mainService;
  }

  public void getList() {
    Timber.d("getList");
    ApiInterface apiInterface = ServiceManager.getInstance().getApiInterface();
    if (apiInterface != null) {
//      Call<List<PokeList>> call = apiInterface.getList();
      Call<Pokemon> call = apiInterface.getList();
      call.enqueue(networkResponseHandler);
    }
  }
}
