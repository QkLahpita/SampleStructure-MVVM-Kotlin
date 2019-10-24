package com.example.qklahpita.testmvvmwithapi

import android.app.Activity
import android.app.Application
import com.example.qklahpita.testmvvmwithapi.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CustomApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}