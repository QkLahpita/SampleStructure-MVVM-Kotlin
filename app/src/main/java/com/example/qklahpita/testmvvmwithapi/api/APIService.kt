package com.example.qklahpita.testmvvmwithapi.api

import androidx.lifecycle.LiveData
import com.example.qklahpita.testmvvmwithapi.vo.Category
import retrofit2.http.GET

interface APIService {
    @GET("categories")
    fun getCategories(): LiveData<ApiResponse<List<Category>>>
}