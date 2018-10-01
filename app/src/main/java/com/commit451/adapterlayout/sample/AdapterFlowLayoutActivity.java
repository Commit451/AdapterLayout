package com.commit451.adapterlayout.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.commit451.adapterflowlayout.AdapterFlowLayout;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdapterFlowLayoutActivity extends AppCompatActivity {

    Toolbar toolbar;
    AdapterFlowLayout adapterFlowLayout;

    CheeseAdapter adapter;

    private CheeseAdapter.Listener listener = new CheeseAdapter.Listener() {
        @Override
        public void onItemClicked(Cheese cheese) {
            Toast.makeText(AdapterFlowLayoutActivity.this, cheese.name + " clicked", Toast.LENGTH_SHORT)
                    .show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter_layout);
        toolbar = findViewById(R.id.toolbar);
        adapterFlowLayout = findViewById(R.id.adapter_layout);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        adapter = new CheeseAdapter(listener);
        adapterFlowLayout.setAdapter(adapter);

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
        findViewById(R.id.new_adapter).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                adapterFlowLayout.setAdapter(null);
                adapter = new CheeseAdapter(listener);
                adapterFlowLayout.setAdapter(adapter);
            }
        });
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
