package com.example.qklahpita.testmvvmwithapi.di

import com.example.qklahpita.testmvvmwithapi.feature.TestFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeTestFragment(): TestFragment
}