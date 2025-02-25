package com.example.earthquake_app.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquake_app.DetailActivity
import com.example.earthquake_app.Earthquake
import com.example.earthquake_app.R
import com.example.earthquake_app.api.ApiResponseStatus
import com.example.earthquake_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val eqRecycler = binding.eqRecycler
        eqRecycler.layoutManager = LinearLayoutManager(this)

        val adapter = EqAdapter()
        eqRecycler.adapter = adapter
        viewModel = ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when(itemId){
            R.id.main_menu_sort_time -> viewModel.reloadEarthquakesByDatabase(false )
            R.id.main_menu_sort_magnitude -> viewModel.reloadEarthquakesByDatabase(true)
        }
        return super.onOptionsItemSelected(item)
    }
}