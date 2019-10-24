package com.example.qklahpita.testmvvmwithapi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.qklahpita.testmvvmwithapi.vo.Category

@Database(
    entities = [Category::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
}