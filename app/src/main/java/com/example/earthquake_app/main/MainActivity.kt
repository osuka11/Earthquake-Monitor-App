package com.example.earthquake_app.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquake_app.Earthquake
import com.example.earthquake_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val eqRecycler = binding.eqRecycler
        eqRecycler.layoutManager = LinearLayoutManager(this)

        val adapter = EqAdapter()
        eqRecycler.adapter = adapter
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.eqList.observe(this) { eqList ->
            adapter.submitList(eqList)
            handleEmptyView(eqList, binding)

        }
        adapter.onItemClickListener = {
            Toast.makeText(this,it.place,Toast.LENGTH_SHORT).show()
        }



    }
    private fun handleEmptyView(eqList: MutableList<Earthquake>, binding: ActivityMainBinding) {
        if (eqList.isEmpty()) {
            binding.emptyView.visibility = View.VISIBLE
        } else {
            binding.emptyView.visibility = View.GONE

        }
    }
}