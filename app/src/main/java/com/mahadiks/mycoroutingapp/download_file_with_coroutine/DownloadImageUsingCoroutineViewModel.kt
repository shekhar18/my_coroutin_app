package com.mahadiks.mycoroutingapp.download_file_with_coroutine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class DownloadImageUsingCoroutineViewModel : ViewModel() {

    private val _updateUiState: MutableLiveData<String> = MutableLiveData<String>("Idle")
    val updateUiState: LiveData<String> = _updateUiState

    private val _updateImage: MutableLiveData<Bitmap> = MutableLiveData<Bitmap>()
    val updateImage: LiveData<Bitmap> = _updateImage

    private val _errorMessage: MutableLiveData<String> = MutableLiveData<String>("")
    val errorMessage: LiveData<String> = _errorMessage


    /*private val imageURL =
        "https://fastly.picsum.photos/id/434/200/300.jpg?hmac=OXYWBdR0QE1mGM2dKi1dktPcY5GckI3ClAgMsyszU-I"
    private val imageURL =
        "https://upload.wikimedia.org/wikipedia/commons/9/99/Big_Buck_Bunny_Poster_2.jpg"*/
    private val imageURL =
        "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80"

    fun downloadImageFile() {
        _updateUiState.value = "Loading"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inputStream = URL(imageURL).openStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                withContext(Dispatchers.Main) {
                    _updateImage.value = bitmap
                    _updateUiState.value = "Success"
                }

            } catch (e: Exception) {
                _updateUiState.postValue("error")
                _errorMessage.postValue(e.message)
                println(e.message)
            }
        }


    }


}