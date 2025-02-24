package com.example.earthquake_app.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquake_app.Earthquake
import com.example.earthquake_app.api.ApiResponseStatus
import kotlinx.coroutines.launch
import com.example.earthquake_app.database.getDatabase
import java.net.UnknownHostException

private val TAG = MainViewModel::class.java.simpleName

class MainViewModel(application: Application):AndroidViewModel(application) {
    /*
    private var _eqList= MutableLiveData<MutableList<Earthquake>>()
    val eqList: LiveData<MutableList<Earthquake>>
        get() = _eqList

     */
    /*
    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

     */
    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus> get() = _status

    private val database = getDatabase(application.applicationContext)
    private val repository = MainRepository(database)

    val eqList = repository.eqList

    init {
        viewModelScope.launch {
            try {
                _status.value = ApiResponseStatus.LOADING
                repository.fetchEarthquakes()
                _status.value = ApiResponseStatus.DONE

            }catch (error:UnknownHostException){
                _status.value = ApiResponseStatus.ERROR
                Log.d(TAG, "No internet Connection")
            }
        }

    }
}