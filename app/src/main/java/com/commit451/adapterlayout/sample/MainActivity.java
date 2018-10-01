package com.commit451.adapterlayout.sample;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.commit451.adapterlayout.AdapterLinearLayout;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    ViewGroup root;
    Toolbar toolbar;
    AdapterLinearLayout adapterLinearLayout;
    CheckBox checkBoxAnimateLayoutChanges;

    CheeseAdapter adapter;

    private CheeseAdapter.Listener listener = new CheeseAdapter.Listener() {
        @Override
        public void onItemClicked(Cheese cheese) {
            Snackbar.make(root, cheese.name + " clicked", Snackbar.LENGTH_SHORT)
                    .show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = findViewById(R.id.root);
        toolbar = findViewById(R.id.toolbar);
        adapterLinearLayout = findViewById(R.id.adapter_layout);
        checkBoxAnimateLayoutChanges = findViewById(R.id.animate_layout_changes);
        findViewById(R.id.add_cheese).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                adapter.add(Cheeses.getRandomCheese());
            }
        });
        findViewById(R.id.remove_cheese).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                adapter.removeLast();
            }
        });
        findViewById(R.id.change_middle).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                adapter.changeMiddle();
            }
        });
        findViewById(R.id.clear_all).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                adapter.clear();
            }
        });
        findViewById(R.id.change_all).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                adapter.changeAll();
            }
        });
        findViewById(R.id.set_to_5).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                adapter.setData(Cheeses.getRandomCheeses(5));
            }
        });
        findViewById(R.id.new_adapter).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                adapterLinearLayout.setAdapter(null);
                adapter = new CheeseAdapter(listener);
                adapterLinearLayout.setAdapter(adapter);
            }
        });
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_adapter_flow_layout:
                        startActivity(new Intent(MainActivity.this, AdapterFlowLayoutActivity.class));
                        return true;
                }
                return false;
            }
        });
        adapter = new CheeseAdapter(listener);
        adapterLinearLayout.setAdapter(adapter);

        checkBoxAnimateLayoutChanges.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    LayoutTransition lt = new LayoutTransition();
                    adapterLinearLayout.setLayoutTransition(lt);
                } else {
                    adapterLinearLayout.setLayoutTransition(null);
                }
            }
        });
        checkBoxAnimateLayoutChanges.setChecked(true);
    }
}
