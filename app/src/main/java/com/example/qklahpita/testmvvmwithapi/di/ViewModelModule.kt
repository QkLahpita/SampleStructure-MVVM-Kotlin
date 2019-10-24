package com.example.qklahpita.testmvvmwithapi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.qklahpita.testmvvmwithapi.feature.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//for view model has constructor with args
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel : MainViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory
}