package com.farhansolih0009.miniproject.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farhansolih0009.miniproject.database.MahasiswaDao
import com.farhansolih0009.miniproject.ui.screen.DetailViewModel
import com.farhansolih0009.miniproject.ui.screen.MainViewModel

class ViewModelFactory(
    private val dao: MahasiswaDao
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}