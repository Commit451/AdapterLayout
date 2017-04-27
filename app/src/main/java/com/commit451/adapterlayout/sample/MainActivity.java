package com.commit451.adapterlayout.sample;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.commit451.adapterlayout.AdapterLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.root)
    ViewGroup root;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.adapter_layout)
    AdapterLinearLayout adapterLinearLayout;
    @Bind(R.id.animate_layout_changes)
    CheckBox checkBoxAnimateLayoutChanges;

    CheeseAdapter adapter;

    private CheeseAdapter.Listener mListener = new CheeseAdapter.Listener() {
        @Override
        public void onItemClicked(Cheese cheese) {
            Snackbar.make(root, cheese.getName() + " clicked", Snackbar.LENGTH_SHORT)
                    .show();
        }
    };

    @OnClick(R.id.add_cheese)
    void onAddCheeseClicked() {
        adapter.add(Cheeses.getRandomCheese());
    }

    @OnClick(R.id.remove_cheese)
    void onRemoveCheeseClicked() {
        adapter.removeLast();
    }

    @OnClick(R.id.change_middle)
    void onRemoveMiddleClicked() {
        adapter.changeMiddle();
    }

    @OnClick(R.id.clear_all)
    void onClearAll() {
        adapter.clear();
    }

    @OnClick(R.id.change_all)
    void onChangeAll() {
        adapter.changeAll();
    }

    @OnClick(R.id.new_adapter)
    void onNewAdapterClicked() {
        adapter.setData(Cheeses.getRandomCheeses(5));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_custom_adapter_layout:
                        startActivity(new Intent(MainActivity.this, CustomAdapterLayoutActivity.class));
                        return true;
                }
                return false;
            }
        });
        adapter = new CheeseAdapter(new CheeseAdapter.Listener() {
            @Override
            public void onItemClicked(Cheese cheese) {

            }
        });
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

        adapter.setData(Cheeses.getRandomCheeses(10));
    }
}
