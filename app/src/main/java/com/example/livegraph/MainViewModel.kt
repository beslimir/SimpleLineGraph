package com.example.livegraph

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val numbers: MutableLiveData<Int> = MutableLiveData()

    init {
        getNumbers()
    }

    //push every second a nev value for y axis
    fun getNumbers() = viewModelScope.launch {
        for (i in 1..30) {
            numbers.postValue(i)
            delay(1000L)
        }

    }
}