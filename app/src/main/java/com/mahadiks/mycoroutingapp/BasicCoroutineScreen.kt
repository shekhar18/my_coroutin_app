package com.mahadiks.mycoroutingapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner


@Composable
fun BasicCoroutineScreen(modifier: Modifier = Modifier,  viewModel: BasicCoroutineScreenViewModel,context: LifecycleOwner) {


    var state by remember { mutableStateOf("") }

    viewModel.updateCoroutinesState.observe( context,{
        state = it
    })
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            viewModel.startCoroutine()
        }) { Text("Launch Coroutine") }
        Spacer(modifier = Modifier.height(10.dp))


        Text(state)


    }


}