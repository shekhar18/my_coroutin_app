package com.mahadiks.mycoroutingapp.time_and_cancelling

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.mahadiks.mycoroutingapp.MainActivity

@Composable
fun TimeoutAndCancelingScreen(
    modifier: Modifier = Modifier,
    viewModel: TimeoutAndCancelingViewModel,
    activity: MainActivity
) {
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            viewModel.startCoroutine(withoutTimeOut = false)
            Toast.makeText(context, "Coroutine Started", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Start Coroutine with time out")
        }
        Button(onClick = {
            viewModel.startCoroutine(withoutTimeOut = true)
            Toast.makeText(context, "Coroutine Started", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Start Coroutine without time out")
        }
        Button(onClick = {

            Toast.makeText(context, viewModel.checkCoroutineIsActive().toString(), Toast.LENGTH_SHORT).show() }) {
            Text(text = "Is Active Coroutine")
        }
        Button(onClick = {
            viewModel.cancelCoroutine()
            Toast.makeText(context, "Coroutine Cancel", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Cancel Coroutine")
        }

    }


}