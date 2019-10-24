package com.example.qklahpita.testmvvmwithapi.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.qklahpita.testmvvmwithapi.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    //1. create folders: api, db, di, vo
    //2. implement lifecycle & retrofit & dagger in gradle

    //Note about dagger: class: A; class member: B; interface to init di member: C
    //1. create B then @Inject constructor()
    //2. @Inject lateinit var B in A
    //3. @Component C { ... } then rebuild to make DaggerC
    //4. DaggerC.create()... in A

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.getAllCategories().observe(this, Observer {
            tv_test.text = it.data?.size.toString()
        })
    }
}
