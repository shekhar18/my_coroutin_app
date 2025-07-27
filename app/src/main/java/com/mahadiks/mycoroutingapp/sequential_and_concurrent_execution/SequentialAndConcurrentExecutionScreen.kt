package com.mahadiks.mycoroutingapp.sequential_and_concurrent_execution

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
fun SequentialAndConcurrentExecutionScreen(
    modifier: Modifier = Modifier,
    viewModel: SequentialAndConcurrentExecutionViewModel,
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
                Button(onClick = { viewModel.useLaunch() }) {
                    Text("Use Launch")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { viewModel.useAsync() }) {
                    Text("Use Async")

                }
                Spacer(modifier = Modifier.height(10.dp))

                Text("Launch Time Taken: ${viewModel.updateLaunchTimeTaken.value} ms")
                viewModel.updateImage.observeAsState().value?.asImageBitmap()?.let {
                    Image(
                        painter = BitmapPainter(it),
                        contentDescription = "Downloaded Image",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                }
                Spacer(modifier = modifier.height(10.dp))
                viewModel.updateUserData.observeAsState().value?.let { Text(it) }
                Spacer(modifier = modifier.height(20.dp))
                Text("Async Time Taken: ${viewModel.updateAsyncTimeTaken.value} ms")
                viewModel.updateAsyncImage.observeAsState().value?.asImageBitmap()?.let {
                    Image(
                        painter = BitmapPainter(it),
                        contentDescription = "Downloaded Image",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                }
                Spacer(modifier = modifier.height(10.dp))
                viewModel.updateAsyncUserData.observeAsState().value?.let { Text(it) }
            }

            "error" -> {

            }
        }


    }


}