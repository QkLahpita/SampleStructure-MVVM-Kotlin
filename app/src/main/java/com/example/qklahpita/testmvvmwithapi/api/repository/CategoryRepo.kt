package com.example.qklahpita.testmvvmwithapi.api.repository

import androidx.lifecycle.LiveData
import com.example.qklahpita.testmvvmwithapi.api.APIService
import com.example.qklahpita.testmvvmwithapi.api.ApiResponse
import com.example.qklahpita.testmvvmwithapi.api.NetworkBoundResource
import com.example.qklahpita.testmvvmwithapi.api.Resource
import com.example.qklahpita.testmvvmwithapi.db.CategoryDao
import com.example.qklahpita.testmvvmwithapi.vo.Category
import javax.inject.Inject

class CategoryRepo @Inject constructor(private val apiService: APIService, private val repoDao: CategoryDao) {

    fun getCategories() : LiveData<Resource<List<Category>>> {
        return object : NetworkBoundResource<List<Category>, List<Category>>() {
            override fun saveCallResult(item: List<Category>) {
                repoDao.insertCategories(item)
            }

            override fun shouldFetch(data: List<Category>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Category>> {
                return repoDao.loadCategoriesFromDb()
            }

            override fun createCall(): LiveData<ApiResponse<List<Category>>> {
                return apiService.getCategories()
            }

        }.asLiveData()
    }

}