package io.johan.roommvp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import io.johan.roommvp.R;
import io.johan.roommvp.adapter.PokeListAdapter;
import io.johan.roommvp.data.contract.MainActivityContract;
import io.johan.roommvp.data.entity.PokeList;
import io.johan.roommvp.presenter.MainActivityPresenter;
import java.util.List;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

  public static int DETAIL_ACT_REQUESTCODE = 1001;
  public static String NEWNAME = "newname";
  public static String OLDNAME = "oldname";
  public static String POSITION = "position";

  @BindView(R.id.rc)
  RecyclerView rc;
  @BindView(R.id.loadingbar)
  ProgressBar loadingbar;
  @BindView(R.id.loadingText)
  TextView loadingText;

  PokeListAdapter adapter;
  MainActivityPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    presenter = new MainActivityPresenter(this);
    Timber.d("onCreate");
    Timber.d("onCreateTimber");

    rc.setHasFixedSize(true);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    rc.setLayoutManager(linearLayoutManager);

    presenter.getList();
  }

  @Override
  public void showLoadingBar() {
    loadingbar.setVisibility(View.VISIBLE);
    loadingText.setVisibility(View.VISIBLE);
    loadingText.setText(getResources().getText(R.string.title_loading));
  }

  @Override
  public void removeLoadingBar() {
    loadingbar.setVisibility(View.GONE);
    loadingText.setVisibility(View.GONE);
  }

  @Override
  public void onSuccess(List<PokeList> lists) {
    loadingbar.setVisibility(View.GONE);
    loadingText.setVisibility(View.GONE);

    rc.setVisibility(View.VISIBLE);
    adapter =  new PokeListAdapter(this, lists);
    rc.setItemAnimator(new DefaultItemAnimator());
    rc.setAdapter(adapter);
  }

  @Override
  public void setItemInAdapter(int positionItem, PokeList pokeList) {
    Timber.d("setItemInAdapter position %s", positionItem);
    Timber.d("new name is %s", pokeList.getName());
    adapter.setItem(positionItem, pokeList);
  }

  @Override
  public void onError(String errorMessage) {
    loadingText.setVisibility(View.VISIBLE);
    loadingText.setText(errorMessage);
  }

  public void openDetailActivity(PokeList object, int position) {
    Timber.d("openDetail with object %s", object);
    Intent intent = new Intent(this, DetailedActivity.class);
    intent.putExtra(OLDNAME, object);
    intent.putExtra(POSITION, position);
    startActivityForResult(intent, DETAIL_ACT_REQUESTCODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Timber.d("onActivityResult " + requestCode + " resultCode " + resultCode);
    if (requestCode == DETAIL_ACT_REQUESTCODE) {

      if (resultCode == RESULT_OK) {
        assert data != null;
        String oldPokeJson = data.getStringExtra(OLDNAME);
        String newPokeJson = data.getStringExtra(NEWNAME);

        Gson gson = new Gson();
        PokeList oldPoke = gson.fromJson(oldPokeJson, PokeList.class);
        PokeList newPoke = gson.fromJson(newPokeJson, PokeList.class);
        if (oldPoke != null && newPoke != null) {
          int position = data.getIntExtra(POSITION, 0);
          Timber.d("old name returned is %s", oldPoke.getName());
          Timber.d("new name returned is %s", newPoke.getName());
          Timber.d("Position is %s", position);
          presenter.update(position, oldPoke, newPoke);
        } else {
          Timber.e("Returned NAMEPASS is null or empty.");
        }
      }
    }
  }
}
