package io.johan.roommvp.activities;

import static io.johan.roommvp.activities.MainActivity.NEWNAME;
import static io.johan.roommvp.activities.MainActivity.OLDNAME;
import static io.johan.roommvp.activities.MainActivity.POSITION;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;
import io.johan.roommvp.R;
import io.johan.roommvp.data.entity.PokeList;
import timber.log.Timber;

public class DetailedActivity extends AppCompatActivity {

  @BindView(R.id.et_name)
  EditText etName;

  PokeList defaultItem;
  int position;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detailed);
    ButterKnife.bind(this);
    Bundle bundle = getIntent().getExtras();
    if (getIntent().getExtras() != null ) {
      defaultItem = bundle.getParcelable(OLDNAME);

      position = getIntent().getIntExtra(POSITION, 0);
      Timber.d("Received namepass %s", defaultItem.getName() + " position " + position);
      etName.setText(defaultItem.getName());
    } else {
      Timber.e("Error no data send to this activity!");
    }
  }

  @OnClick(R.id.btn_submit)
  public void onClick() {

    Intent data = new Intent();
    if (!defaultItem.getName().trim().equalsIgnoreCase(etName.getText().toString())) {
      Timber.d("Default name not equal to etName");
      Timber.d("Default data " + defaultItem.getName() + " equal to edit name " + etName.getText().toString());

      Gson gson = new Gson();

      String defaultDataInString = gson.toJson(defaultItem);
      data.putExtra(OLDNAME, defaultDataInString);

      PokeList newPokeList = defaultItem;
      newPokeList.setName(etName.getText().toString());

      String newDataInString = gson.toJson(newPokeList);
      data.putExtra(NEWNAME, newDataInString);

      data.putExtra(POSITION, position);
    } else {
      Timber.e("Default data equal to etName, due to not change anything!!");
    }
    setResult(RESULT_OK,data);
    finish();
  }
}
