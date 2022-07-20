package com.example.jetpackdatastore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repo = ProtoDataStore(application)
    val text = repo.readData.asLiveData()
    fun updateValue(text: String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateValue(text)
        }
    }
}