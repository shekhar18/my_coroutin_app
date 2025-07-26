package com.mahadiks.mycoroutingapp.structured_concurrency

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mahadiks.mycoroutingapp.MainActivity

@Composable
fun StructureConcurrencyScreen(
    modifier: Modifier = Modifier,
    viewModel: StructureConcurrencyScreenViewModel,
    activity: MainActivity
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { viewModel.startShow() }) {
            Text("start the show")
        }

        Button(onClick = { viewModel.stopShow() }) {
            Text("stop the show")
        }
    }


}