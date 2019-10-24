package com.example.qklahpita.testmvvmwithapi.di

import android.app.Application
import androidx.room.Room
import com.example.qklahpita.testmvvmwithapi.BuildConfig
import com.example.qklahpita.testmvvmwithapi.api.APIService
import com.example.qklahpita.testmvvmwithapi.api.LiveDataCallAdapterFactory
import com.example.qklahpita.testmvvmwithapi.db.AppDatabase
import com.example.qklahpita.testmvvmwithapi.db.CategoryDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideAPIService(okHttpClient: OkHttpClient): APIService {
        return Retrofit.Builder()
            .baseUrl("https://health0910.herokuapp.com/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "app_db.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(db: AppDatabase): CategoryDao {
        return db.categoryDao()
    }

}