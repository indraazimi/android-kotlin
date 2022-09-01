package com.indraazimi.helloworld

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val data = MutableLiveData<List<Hewan>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveDataFromServer()
    }

    private fun retrieveDataFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                status.postValue(ApiStatus.LOADING)
                val result = HewanApi.service.getHewan()
                status.postValue(ApiStatus.SUCCESS)
                data.postValue(result)
            } catch (e: Exception) {
                status.postValue(ApiStatus.FAILED)
                Log.d("MainViewModel", "Gagal: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Hewan>> = data

    fun getStatus(): LiveData<ApiStatus> = status
}