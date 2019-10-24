package com.example.qklahpita.testmvvmwithapi.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.qklahpita.testmvvmwithapi.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_fragment_inside.*
import javax.inject.Inject

class FragmentInsideActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_inside)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onSupportNavigateUp() = NavHostFragment.findNavController(nav_host_fragment).navigateUp()
}
