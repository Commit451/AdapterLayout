package com.commit451.adapterlayout.sample

import android.animation.LayoutTransition
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.commit451.adapterlayout.AdapterLinearLayout

class MainActivity : AppCompatActivity() {

    private lateinit var root: ViewGroup
    private lateinit var toolbar: Toolbar
    private lateinit var adapterLinearLayout: AdapterLinearLayout
    private lateinit var checkBoxAnimateLayoutChanges: CheckBox

    private lateinit var adapter: CheeseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listener = { cheese: Cheese ->
            Toast.makeText(this, "${cheese.name} clicked", Toast.LENGTH_SHORT)
                .show()
        }
        root = findViewById(R.id.root)
        toolbar = findViewById(R.id.toolbar)
        adapterLinearLayout = findViewById(R.id.adapter_layout)
        checkBoxAnimateLayoutChanges = findViewById(R.id.animate_layout_changes)
        findViewById<View>(R.id.add_cheese).setOnClickListener { adapter.add(Cheeses.randomCheese) }
        findViewById<View>(R.id.remove_cheese).setOnClickListener { adapter.removeLast() }
        findViewById<View>(R.id.change_middle).setOnClickListener { adapter.changeMiddle() }
        findViewById<View>(R.id.clear_all).setOnClickListener { adapter.clear() }
        findViewById<View>(R.id.change_all).setOnClickListener { adapter.changeAll() }
        findViewById<View>(R.id.set_to_5).setOnClickListener { adapter.setData(Cheeses.getRandomCheeses(5)) }
        findViewById<View>(R.id.new_adapter).setOnClickListener {
            adapterLinearLayout.setAdapter(null)
            adapter = CheeseAdapter(listener)
            adapterLinearLayout.setAdapter(adapter)
        }
        toolbar.setTitle(R.string.app_name)
        toolbar.inflateMenu(R.menu.main)
        toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_adapter_flow_layout -> {
                    startActivity(Intent(this@MainActivity, AdapterFlowLayoutActivity::class.java))
                    return@OnMenuItemClickListener true
                }
            }
            false
        })
        adapter = CheeseAdapter(listener)
        adapterLinearLayout.adapter = adapter

        checkBoxAnimateLayoutChanges.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val layoutTransition = LayoutTransition()
                adapterLinearLayout.layoutTransition = layoutTransition
            } else {
                adapterLinearLayout.layoutTransition = null
            }
        }
        checkBoxAnimateLayoutChanges.isChecked = true
    }
}
