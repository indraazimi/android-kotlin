package com.indraazimi.helloworld

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.indraazimi.helloworld.database.DiaryDao

class MainViewModel(db: DiaryDao) : ViewModel() {
    val data = db.getDiaries()
}

class MainViewModelFactory(private val db: DiaryDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(db) as T
        }
        throw IllegalArgumentException("ViewModel tidak ada")
    }
}