package com.commit451.adapterlayout.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * The view holder related to each Cheese item
 */
public class CheeseViewHolder extends RecyclerView.ViewHolder {

    public static CheeseViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cheese, parent, false);
        return new CheeseViewHolder(view);
    }

    public TextView title;
    public View buttonRemove;

    public CheeseViewHolder(View view) {
        super(view);
        title = view.findViewById(R.id.text);
        buttonRemove = view.findViewById(R.id.button_remove);
    }

    public void bind(Cheese cheese) {
        title.setText(cheese.name);
    }
}
