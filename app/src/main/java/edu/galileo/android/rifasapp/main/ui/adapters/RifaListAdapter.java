package edu.galileo.android.rifasapp.main.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.rifasapp.R;
import edu.galileo.android.rifasapp.entities.Rifa;

/**
 * Created by praxis on 11/07/16.
 */
public class RifaListAdapter extends RecyclerView.Adapter<RifaListAdapter.ViewHolder> {
    private List<Rifa> rifaList;
    private OnItemClickListener onItemClickListener;

    public RifaListAdapter(List<Rifa> rifaList, OnItemClickListener onItemClickListener) {
        this.rifaList = rifaList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_rifa, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rifa currentRifa = rifaList.get(position);

        holder.title.setText(currentRifa.getName());
        holder.date.setText(currentRifa.getDate());

        holder.setOnItemClickListener(currentRifa, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return rifaList.size();
    }

    public void setRifas(List<Rifa> rifas){
        this.rifaList = rifas;
        notifyDataSetChanged();
    }

    public void removeRifa(Rifa rifa){
        rifaList.remove(rifa);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.date)
        TextView date;
        @Bind(R.id.imgDelete)
        ImageButton imgDelete;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final Rifa currentRifa, final OnItemClickListener onItemCLickListener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemCLickListener.onItemCLick(currentRifa);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemCLickListener.onDeleteClick(currentRifa);
                }
            });
        }
    }
}
