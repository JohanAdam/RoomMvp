package io.johan.roommvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import io.johan.roommvp.R;
import io.johan.roommvp.data.entity.PokeList;
import java.util.List;
import timber.log.Timber;

public class PokeListAdapter extends RecyclerView.Adapter<PokeListAdapter.ViewHolder> {

  private Context context;
  private List<PokeList> lists;

  public PokeListAdapter(Context context, List<PokeList> lists) {
    this.context = context;
    this.lists = lists;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    final PokeList poke = lists.get(viewHolder.getAdapterPosition());
    viewHolder.bind(poke);

    viewHolder.itemView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Timber.d("onClick dat shet");
      }
    });
  }

  @Override
  public int getItemCount() {
    if (lists != null) {
      return lists.size();
    }
    return 0;
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    TextView tvname;

    ViewHolder(@NonNull View itemView) {
      super(itemView);
      tvname = itemView.findViewById(R.id.tv_name);
    }

    void bind(PokeList poke) {
      tvname.setText(poke.getName());
    }
  }
}
