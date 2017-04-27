package com.commit451.adapterlayout.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.commit451.adapterflowlayout.AdapterFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterFlowLayoutActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.adapter_layout)
    AdapterFlowLayout adapterFlowLayout;

    CheeseAdapter adapter;

    private CheeseAdapter.Listener listener = new CheeseAdapter.Listener() {
        @Override
        public void onItemClicked(Cheese cheese) {
            Toast.makeText(AdapterFlowLayoutActivity.this, cheese.name + " clicked", Toast.LENGTH_SHORT)
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

    @OnClick(R.id.new_adapter)
    void onNewAdapterClicked() {
        adapterFlowLayout.setAdapter(null);
        adapter = new CheeseAdapter(listener);
        adapterFlowLayout.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter_layout);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        adapter = new CheeseAdapter(listener);
        adapterFlowLayout.setAdapter(adapter);
        loadCheeses();
    }

    private void loadCheeses() {
        ArrayList<Cheese> cheeses = new ArrayList<>();
        for (int i=0; i<5; i++) {
            cheeses.add(Cheeses.getRandomCheese());
        }
        adapter.setData(cheeses);
    }
}
