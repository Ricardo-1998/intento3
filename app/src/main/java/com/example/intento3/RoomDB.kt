package com.example.intento3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.intento3.dao.MoviesDao
import com.example.intento3.entities.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun movieDao():MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(
            context: Context
        ): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, RoomDB::class.java, "Repo_Database")
                    .build()
                INSTANCE=instance
                return instance
            }

        }

    }
}