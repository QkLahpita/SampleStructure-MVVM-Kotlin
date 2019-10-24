package com.example.qklahpita.testmvvmwithapi.di

import android.app.Application
import com.example.qklahpita.testmvvmwithapi.CustomApplication
import com.example.qklahpita.testmvvmwithapi.feature.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

//structure
//component -- module (activity) -- functions to provide injection objects

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        FragmentInsideActivityModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: CustomApplication)
}