package com.mahadiks.mycoroutingapp.time_and_cancelling

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class TimeoutAndCancelingViewModel : ViewModel() {


    //in this example we see the timeout, cancelling and isActive coroutines.

    val scope = CoroutineScope(Dispatchers.IO)
    val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(
            "MyViewModel", "Handled Exception: ${throwable.message}"
        )
    }
    private var job: Job? = null

    fun startCoroutine(withoutTimeOut: Boolean = false) {

        if (withoutTimeOut) {
            job = scope.launch {
                try {
                    repeat(100) {
                        delay(1000)
                        Log.d("MyViewModel", "Data: $it")
                    }
                } catch (e: Exception) {
                    //  Log.d("MyViewModel", "Exception: ${e.message}")
                    throw e
                }


            }
        } else {
            job = scope.launch {
                //when we use withTimeout we need to handle the
                // exception in side of withTimeout block because it get suppress in withTimeout
                withTimeout(3000) {
                    try {
                        val data = getDataFromServer()
                        Log.d("MyViewModel", "Data: $data")
                    } catch (e: Exception) {
                        Log.d("MyViewModel", "Exception: ${e.message}")
                    }

                }
            }
        }
    }


    suspend fun getDataFromServer(): String {
        delay(5000)
        return "Data Get From Server"
    }


    fun checkCoroutineIsActive(): String {
        var message = ""
        job?.let {
            if (it.isActive) {
                Log.d("MyViewModel", "Coroutine is active")
                message = "Coroutine is active"
            } else {
                Log.d("MyViewModel", "Coroutine is not active")
                message = "Coroutine is not active"

            }
        }
        return message
    }


    fun cancelCoroutine() {
        job?.let {
            if (it.isActive) {
                it.cancel()
                Log.d("MyViewModel", "Coroutine is cancelled")
            }
        }
    }


}