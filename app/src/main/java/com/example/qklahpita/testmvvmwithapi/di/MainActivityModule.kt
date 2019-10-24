package com.example.qklahpita.testmvvmwithapi.di

import com.example.qklahpita.testmvvmwithapi.feature.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}