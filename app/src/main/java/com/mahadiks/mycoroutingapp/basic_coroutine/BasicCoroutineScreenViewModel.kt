package com.mahadiks.mycoroutingapp.basic_coroutine

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class BasicCoroutineScreenViewModel() : ViewModel() {

    //in this Below Example we use Coroutine which execute the code on main thread
    // and when we try to update the liveData we need to use setValue method to update the liveData
    //if we use Dispatchers.IO then we need to use postValue method to update the liveData because we updating livedata from background thread.
    private val _updateCoroutinesState: MutableLiveData<String> =
        MutableLiveData<String>("Coroutine is not running")
    val updateCoroutinesState: LiveData<String> = _updateCoroutinesState

    fun startCoroutine() {
        viewModelScope.launch {
            Log.d("mahadiks", "startCoroutine: ${Thread.currentThread().name}")
            _updateCoroutinesState.value = "Coroutine is running on ${Thread.currentThread().name}"
            //here we getting the data from server using flow and collect method we use to collect he data which is emitted by producer
            getTheQuote().collect {
                _updateCoroutinesState.value = it
                Log.d("mahadiks", "Data: $it")
            }

        }
    }
}

//here we mock the repo like taking time to fetch the data from server
suspend fun getTheQuote(): Flow<String> {
    delay(5000)
    return flow {
        emit("This is the quote")
    }
}