package com.example.earthquake_app.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquake_app.Earthquake
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private var _eqList= MutableLiveData<MutableList<Earthquake>>()
    val eqList: LiveData<MutableList<Earthquake>>
        get() = _eqList
    /*
    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

     */
    private val repository = MainRepository()

    init {
        viewModelScope.launch {
            _eqList.value = repository.fetchEarthquakes()
        }

    }
}