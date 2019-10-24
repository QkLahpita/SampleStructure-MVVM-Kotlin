package com.example.qklahpita.testmvvmwithapi.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qklahpita.testmvvmwithapi.vo.Category

@Dao
interface CategoryDao {
    @Query("select * from Category ")
    fun loadCategoriesFromDb(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(countries: List<Category>)
}