package com.example.library.libdb

import androidx.lifecycle.LiveData

// this class will abstract access to multiple data sources
class LibRepo(private val libDao: LibDao) {

    val readAllBook:LiveData<List<Lib>> = libDao.readAllBook()

    suspend fun addBook(lib: Lib){
        libDao.addBook(lib)
    }
}