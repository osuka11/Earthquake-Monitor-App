package com.example.earthquake_app.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquake_app.DetailActivity
import com.example.earthquake_app.Earthquake
import com.example.earthquake_app.api.ApiResponseStatus
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
        val viewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)

        viewModel.eqList.observe(this) { eqList ->
            adapter.submitList(eqList)
            handleEmptyView(eqList, binding)


        }
        viewModel.status.observe(this) {
            apiResponseStatus ->
            when (apiResponseStatus) {
                ApiResponseStatus.LOADING -> binding.progressBar.visibility = View.VISIBLE
                ApiResponseStatus.DONE -> binding.progressBar.visibility = View.GONE
                ApiResponseStatus.ERROR -> binding.progressBar.visibility = View.GONE
            }
        }
        adapter.onItemClickListener = {
            openDetailActivity(it)


        }



    }

    private fun openDetailActivity(earthquake: Earthquake) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EARTHQUAKE, earthquake)
        startActivity(intent)


    }

    private fun handleEmptyView(eqList: MutableList<Earthquake>, binding: ActivityMainBinding) {
        if (eqList.isEmpty()) {
            binding.emptyView.visibility = View.VISIBLE
        } else {
            binding.emptyView.visibility = View.GONE

        }
    }
}