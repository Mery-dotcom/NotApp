package com.geeks.notapp.data.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val selectedColor = MutableLiveData<Int>()

    fun setColor(color: Int){
        selectedColor.value = color
    }
}