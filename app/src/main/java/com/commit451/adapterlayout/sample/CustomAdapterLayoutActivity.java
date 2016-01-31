package com.commit451.adapterlayout.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomAdapterLayoutActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.adapter_layout) AdapterFlowLayout mAdapterLayout;
    CheeseAdapter mAdapter;

    @OnClick(R.id.add_cheese)
    void onAddCheeseClicked() {
        mAdapter.add(Cheeses.getRandomCheese());
    }

    @OnClick(R.id.remove_cheese)
    void onRemoveCheeseClicked() {
        mAdapter.removeLast();
    }

    @OnClick(R.id.new_adapter)
    void onNewAdapterClicked() {
        mAdapterLayout.setAdapter(null);
        mAdapter = new CheeseAdapter(mListener);
        mAdapterLayout.setAdapter(mAdapter);
    }

    private CheeseAdapter.Listener mListener = new CheeseAdapter.Listener() {
        @Override
        public void onItemClicked(Cheese cheese) {
            Toast.makeText(CustomAdapterLayoutActivity.this, cheese.getName() + " clicked", Toast.LENGTH_SHORT)
                    .show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter_layout);
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mAdapter = new CheeseAdapter(mListener);
        mAdapterLayout.setAdapter(mAdapter);
        loadCheeses();
    }

    private void loadCheeses() {
        ArrayList<Cheese> cheeses = new ArrayList<>();
        for (int i=0; i<5; i++) {
            cheeses.add(Cheeses.getRandomCheese());
        }
        mAdapter.setData(cheeses);
    }
}
