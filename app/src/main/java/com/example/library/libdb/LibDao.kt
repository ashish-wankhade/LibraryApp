package com.example.library.libdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LibDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Lib)

    @Query("SELECT * FROM book_table ORDER BY id ASC")
    fun readAllBook(): LiveData<List<Lib>>
}