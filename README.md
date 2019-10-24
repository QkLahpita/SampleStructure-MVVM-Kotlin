# SampleStructure-MVVM-Kotlin
Using Kotlin, AndroidX, Lifecycle, Room, Retrofit2 with HttpInterceptor, Dagger2

Structure

- api
  |_ repository
    |_ ...Repo
  |_ ApiResponse
  |_ APIService
  |_ LiveDataCallAdapter
  |_ LiveDataCallAdapterFactory
  |_ NetworkBoundResource
  |_ Resource
  |_ Status
  
- db
  |_ AppDatabase
  |_ ...DAO

- di
  |_ AppComponent
  |_ AppInjector
  |_ AppModule
  |_ CustomViewModelFactory
  |_ FragmentBuildersModule
  |_ ...Module (module for activity with fragments)
  |_ Injectable
  |_ ViewModelModule
  |_ ViewModelKey
  
 - feature: screens
  
 - vo (view object)
