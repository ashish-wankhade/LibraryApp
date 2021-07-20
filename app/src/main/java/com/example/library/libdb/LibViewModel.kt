package com.example.library.libdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LibViewModel(application: Application): AndroidViewModel(application) {
    private val readAllBook:LiveData<List<Lib>>
    private val repo: LibRepo

    init {
        val libDao = LibDb.getDatabase(application).libDao()
        repo = LibRepo(libDao)
        readAllBook = repo.readAllBook
    }

    fun addBook(lib: Lib){
        viewModelScope.launch(Dispatchers.IO){
            repo.addBook(lib)
        }
    }
}