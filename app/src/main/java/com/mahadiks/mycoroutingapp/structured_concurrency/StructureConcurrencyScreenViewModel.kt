package com.mahadiks.mycoroutingapp.structured_concurrency

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelFutureOnCompletion
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StructureConcurrencyScreenViewModel : ViewModel() {


    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MyViewModel", "Handled Exception: ${throwable.message}")
    }

    val myScope = CoroutineScope(Dispatchers.IO + SupervisorJob() + errorHandler)
    fun startShow() {

        myScope.launch {
            repeat(5) {
                delay(1000)
                throw RuntimeException("Coroutine 1 crashed!")
            }

        }
        myScope.launch {
            repeat(5) {
                delay(2000)
                Log.d("MyViewModel", "Coroutine 2 running $it")
            }
        }
        myScope.launch {
            repeat(5) {
                delay(5000)
                Log.d("MyViewModel", "Coroutine 3 running $it")
            }

        }

    }

    fun stopShow() {
        myScope.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        myScope.cancel()
        Log.d("MyViewModel", "ViewModel cleared")

    }


}