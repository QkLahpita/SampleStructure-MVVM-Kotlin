package com.example.qklahpita.testmvvmwithapi.api

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * @param <ResultType>: database model
 * @param <RequestType>: from server response
</RequestType></ResultType> */
abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {

        @Suppress("LeakingThis")
        val dbSource = loadFromDb()

        //start to listen the given source LiveData, onChanged observer will be called when source value was changed.
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource) // after got data from db, stop to listen
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource) // listen sth else depend on situation
            } else {
                result.addSource(dbSource) { newData ->
                    // listen data from db again
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        // re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse) { response ->
            println("NetworkBoundResource.fetchFromNetwork $response")
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse<RequestType> -> {
                    onSuccessResponse(response.body)
                }
                is ApiEmptyResponse<RequestType> -> {
                    result.addSource(loadFromDb()) { newData ->
                        setValue(Resource.success(newData))

                    }
                }
                is ApiErrorResponse<RequestType> -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(response.errorMessage, newData))
                    }
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun onSuccessResponse(response: RequestType) {
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                saveCallResult(response)
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                result.addSource(loadFromDb()) { newData -> setValue(Resource.success(newData)) }
            }
        }.execute()
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    //saves the network response into the database. Any mapping of the response into a model object before storing can be done here.
    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    //decides whether the cached data is fresh or not. If not a new network request will be triggered.
    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    //is used to return whatever information is stored in the database
    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    //creates the network request. NetworkBoundResource will take responsibility for triggering the request.
    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}