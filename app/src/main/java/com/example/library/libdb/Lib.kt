package com.example.library.libdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Lib(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val book_name: String,
    val author_name: String,
    val isbn: String
)