package com.mahadiks.mycoroutingapp.download_file_with_coroutine

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import com.mahadiks.mycoroutingapp.MainActivity

@Composable
fun ShowDownloadImageScreen(
    modifier: Modifier = Modifier,
    viewModel: DownloadImageUsingCoroutineViewModel,
    activity: MainActivity
) {


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (viewModel.updateUiState.observeAsState().value) {
            "Loading" -> CircularProgressIndicator()
            "Idle", "Success" -> {
                Button(
                    onClick = {
                        viewModel.downloadImageFile()
                    }) { Text("Start Download") }
                viewModel.updateImage.observeAsState().value?.asImageBitmap()?.let {
                    Image(
                        painter = BitmapPainter(it),
                        contentDescription = "Downloaded Image",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                }
            }

            "error" -> {
                Button(
                    onClick = {
                        viewModel.downloadImageFile()
                    }) { Text("Start Download") }
                Text("${viewModel.errorMessage.observeAsState().value}")
            }

        }

    }
}