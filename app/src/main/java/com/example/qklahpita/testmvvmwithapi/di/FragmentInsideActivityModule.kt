package com.example.qklahpita.testmvvmwithapi.di

import com.example.qklahpita.testmvvmwithapi.feature.FragmentInsideActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentInsideActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeFragmentInsideActivity(): FragmentInsideActivity
}