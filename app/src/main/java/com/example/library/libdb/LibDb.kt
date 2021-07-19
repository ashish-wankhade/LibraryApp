package com.example.library.libdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Lib::class], version = 1, exportSchema = false)
abstract class LibDb: RoomDatabase() {

    abstract fun libDao(): LibDao //data access object

    companion object{
        @Volatile //all things are visible to other
        private var INSTANCE: LibDb? = null //to make only one instance of the class

        fun getDatabase(context: Context): LibDb{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){                     // to protect everything from concurrent execution
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibDb::class.java,
                    "book_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}