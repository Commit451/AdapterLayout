package com.commit451.adapterlayout.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.commit451.adapterlayout.AdapterLayout;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Adapter for the recyclerview, which holds cheeses
 */
public class CheeseAdapter extends RecyclerView.Adapter<CheeseViewHolder> {

    private Listener listener;
    private ArrayList<Cheese> values;

    public CheeseAdapter(Listener listener) {
        this.listener = listener;
        values = new ArrayList<>();
    }

    public void setData(Collection<Cheese> cheeses) {
        values.clear();
        values.addAll(cheeses);
        notifyDataSetChanged();
    }

    public void add(Cheese cheese) {
        values.add(cheese);
        notifyItemInserted(values.size()-1);
    }

    public void removeLast() {
        if (!values.isEmpty()) {
            int removeIndex = values.size() - 1;
            values.remove(removeIndex);
            notifyItemRemoved(removeIndex);
        }
    }

    public void changeMiddle() {
        if (!values.isEmpty()) {
            int index = values.size()/2;
            values.get(index).name = "Swiss";
            notifyItemChanged(index);
        }
    }

    public void changeAll() {
        if (!values.isEmpty()) {
            for (Cheese cheese : values) {
                cheese.name = "Swiss";
            }
            notifyItemRangeChanged(0, values.size());
        }
    }

    public void clear() {
        if (!values.isEmpty()) {
            int size = values.size();
            values.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public CheeseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final CheeseViewHolder holder = CheeseViewHolder.inflate(parent);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Takes the place of holder.getAdapterPosition()
                int position = AdapterLayout.getAdapterPosition(holder);
                Cheese cheese = getItemAt(position);
                listener.onItemClicked(cheese);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final CheeseViewHolder holder, int position) {
        Cheese cheese = getItemAt(position);
        holder.bind(cheese);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    private Cheese getItemAt(int position) {
        return values.get(position);
    }

    public interface Listener {
        void onItemClicked(Cheese cheese);
    }
}
