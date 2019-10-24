package com.example.qklahpita.testmvvmwithapi.feature


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.qklahpita.testmvvmwithapi.R
import com.example.qklahpita.testmvvmwithapi.di.Injectable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

//need to implement Injectable if want to use injection objects
class TestFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.getAllCategories().observe(this, Observer {
            tv_test.text = it.data?.size.toString()
        })
    }

}
