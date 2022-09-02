package com.indraazimi.helloworld

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.indraazimi.helloworld.database.Diary
import com.indraazimi.helloworld.database.DiaryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val db: DiaryDao) : ViewModel() {

    fun insertDiary(diary: Diary) {
        viewModelScope.launch(Dispatchers.IO) {
            db.insert(diary)
        }
    }

    fun updateDiary(diary: Diary) {
        viewModelScope.launch(Dispatchers.IO) {
            db.update(diary)
        }
    }

    fun deleteDiary(diary: Diary) {
        viewModelScope.launch(Dispatchers.IO) {
            db.delete(diary)
        }
    }

    fun getDiary(id: Int) = db.getDiary(id)
}

class DetailViewModelFactory(private val db: DiaryDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(db) as T
        }
        throw IllegalArgumentException("ViewModel tidak ada")
    }
}