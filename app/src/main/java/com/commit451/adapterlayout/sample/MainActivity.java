package com.commit451.adapterlayout.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.commit451.adapterlayout.AdapterLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.root)
    ViewGroup mRoot;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.adapter_layout)
    AdapterLinearLayout mAdapterLinearLayout;
    CheeseAdapter mAdapter;

    @OnClick(R.id.add_cheese)
    void onAddCheeseClicked() {
        mAdapter.add(Cheeses.getRandomCheese());
    }

    @OnClick(R.id.remove_cheese)
    void onRemoveCheeseClicked() {
        mAdapter.removeLast();
    }

    @OnClick(R.id.change_middle)
    void onRemoveMiddleClicked() {
        mAdapter.changeMiddle();
    }

    @OnClick(R.id.clear_all)
    void onClearAll() {
        mAdapter.clear();
    }

    @OnClick(R.id.change_all)
    void onChangeAll() {
        mAdapter.changeAll();
    }

    @OnClick(R.id.new_adapter)
    void onNewAdapterClicked() {
        mAdapterLinearLayout.setAdapter(null);
        mAdapter = new CheeseAdapter(mListener);
        mAdapterLinearLayout.setAdapter(mAdapter);
    }

    private CheeseAdapter.Listener mListener = new CheeseAdapter.Listener() {
        @Override
        public void onItemClicked(Cheese cheese) {
            Snackbar.make(mRoot, cheese.getName() + " clicked", Snackbar.LENGTH_SHORT)
                    .show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.inflateMenu(R.menu.main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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
        mAdapter = new CheeseAdapter(mListener);
        mAdapterLinearLayout.setAdapter(mAdapter);
    }
}
