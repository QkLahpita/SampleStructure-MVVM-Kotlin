package com.example.qklahpita.testmvvmwithapi.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.qklahpita.testmvvmwithapi.api.Resource
import com.example.qklahpita.testmvvmwithapi.api.repository.CategoryRepo
import com.example.qklahpita.testmvvmwithapi.vo.Category
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: CategoryRepo) : ViewModel() {

    private val categories = MediatorLiveData<Resource<List<Category>>>()

    fun getAllCategories(): LiveData<Resource<List<Category>>> {
        categories.addSource(repository.getCategories()) {
            println("MainViewModel.getAllCategories $it")
            categories.value = it
        }
        return categories
    }

}