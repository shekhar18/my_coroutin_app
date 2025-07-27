package com.mahadiks.mycoroutingapp.sequential_and_concurrent_execution

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class SequentialAndConcurrentExecutionViewModel : ViewModel() {

    private val imageURL =
        "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80"

    private val _updateUiState: MutableLiveData<String> = MutableLiveData<String>("Idle")
    val updateUiState: LiveData<String> = _updateUiState

    private val _updateImage: MutableLiveData<Bitmap> = MutableLiveData<Bitmap>()
    val updateImage: LiveData<Bitmap> = _updateImage

    private val _updateUserData: MutableLiveData<String> = MutableLiveData<String>()
    val updateUserData: LiveData<String> = _updateUserData


    private val _updateAsyncImage: MutableLiveData<Bitmap> = MutableLiveData<Bitmap>()
    val updateAsyncImage: LiveData<Bitmap> = _updateAsyncImage
    private val _updateAsyncUserData: MutableLiveData<String> = MutableLiveData<String>()
    val updateAsyncUserData: LiveData<String> = _updateAsyncUserData

    private val _updateLaunchTimeTaken: MutableLiveData<Long> = MutableLiveData<Long>()
    val updateLaunchTimeTaken: LiveData<Long> = _updateLaunchTimeTaken

    private val _updateAsyncTimeTaken: MutableLiveData<Long> = MutableLiveData<Long>()
    val updateAsyncTimeTaken: LiveData<Long> = _updateAsyncTimeTaken


    val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MyViewModel", "Handled Exception: ${throwable.message}")
    }

    val myScope = CoroutineScope(Dispatchers.IO + SupervisorJob() + errorHandler)


    fun useLaunch() {

        val launchTimeTaken = measureTimeMillis {
            _updateUiState.value = "Loading"
            myScope.launch {
                val bitmap = getImage()
                _updateImage.postValue(bitmap)
                withContext(Dispatchers.Main) {
                    _updateUiState.value = "Success"
                }
            }

            myScope.launch {
                val userData = getUserData()
                _updateUserData.postValue(userData)
            }
        }
        _updateLaunchTimeTaken.value = launchTimeTaken


    }

    fun useAsync() {
        _updateUiState.value = "Loading"
        val asyncTimeTaken = measureTimeMillis {

            viewModelScope.launch {
                val differ = myScope.async {
                    val bitmap = getImage()
                    _updateAsyncImage.postValue(bitmap)
                }
                val secondDiffer = myScope.async {
                    val userData = getUserData()
                    _updateAsyncUserData.postValue(userData)
                }
                differ.await()
                secondDiffer.await()
                withContext(Dispatchers.Main){
                    _updateUiState.value = "Success"
                }
            }
        }
        _updateAsyncTimeTaken.value = asyncTimeTaken

    }


    suspend fun getUrl(): String {
        delay(2000)
        return imageURL
    }

    suspend fun getImage(): Bitmap {
        val inputStream = java.net.URL(getUrl()).openStream()
        val bitmap = android.graphics.BitmapFactory.decodeStream(inputStream)
        return bitmap
    }

    suspend fun getUserData(): String {
        delay(2000)
        return "Name: Shekhar Sanjay Mahadik"

    }


}