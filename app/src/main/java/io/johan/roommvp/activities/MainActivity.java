package io.johan.roommvp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.johan.roommvp.adapter.PokeListAdapter;
import io.johan.roommvp.R;
import io.johan.roommvp.data.contract.MainActivityContract;
import io.johan.roommvp.data.entity.PokeList;
import io.johan.roommvp.presenter.MainActivityPresenter;
import java.util.List;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

  @BindView(R.id.rc)
  RecyclerView rc;
  @BindView(R.id.loadingbar)
  ProgressBar loadingbar;
  @BindView(R.id.loadingText)
  TextView loadingText;

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
    PokeListAdapter adapter =  new PokeListAdapter(this, lists);
    rc.setItemAnimator(new DefaultItemAnimator());
    rc.setAdapter(adapter);
  }

  @Override
  public void onError(String errorMessage) {
    loadingText.setVisibility(View.VISIBLE);
    loadingText.setText(errorMessage);
  }

}
