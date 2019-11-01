package com.commit451.adapterlayout.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.commit451.adapterflowlayout.AdapterFlowLayout
import java.util.*

class AdapterFlowLayoutActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var adapterFlowLayout: AdapterFlowLayout

    private lateinit var adapter: CheeseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listener = { cheese: Cheese ->
            Toast.makeText(this, "${cheese.name} clicked", Toast.LENGTH_SHORT)
                .show()
        }
        setContentView(R.layout.activity_custom_adapter_layout)
        toolbar = findViewById(R.id.toolbar)
        adapterFlowLayout = findViewById(R.id.adapter_layout)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        adapter = CheeseAdapter(listener)
        adapterFlowLayout.adapter = adapter

        findViewById<View>(R.id.add_cheese).setOnClickListener { adapter.add(Cheeses.randomCheese) }
        findViewById<View>(R.id.remove_cheese).setOnClickListener { adapter.removeLast() }
        findViewById<View>(R.id.new_adapter).setOnClickListener {
            adapterFlowLayout.adapter = null
            adapter = CheeseAdapter(listener)
            adapterFlowLayout.adapter = adapter
        }
        loadCheeses()
    }

    private fun loadCheeses() {
        val cheeses = ArrayList<Cheese>()
        for (i in 0..4) {
            cheeses.add(Cheeses.randomCheese)
        }
        adapter.setData(cheeses)
    }
}
