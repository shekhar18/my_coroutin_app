package com.mahadiks.mycoroutingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mahadiks.mycoroutingapp.sequential_and_concurrent_execution.SequentialAndConcurrentExecutionScreen
import com.mahadiks.mycoroutingapp.sequential_and_concurrent_execution.SequentialAndConcurrentExecutionViewModel
import com.mahadiks.mycoroutingapp.ui.theme.MyCoroutingAppTheme

class MainActivity : ComponentActivity() {

    //val viewModel: BasicCoroutineScreenViewModel by viewModels()
    //val viewModel: DownloadImageUsingCoroutineViewModel by viewModels()
    //val viewModel: StructureConcurrencyScreenViewModel by viewModels()
    val viewModel: SequentialAndConcurrentExecutionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCoroutingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SequentialAndConcurrentExecutionScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel,
                        this
                    )
                    //StructureConcurrencyScreen(modifier = Modifier.padding(innerPadding), viewModel, this)
                    //ShowDownloadImageScreen(modifier = Modifier.padding(innerPadding), viewModel, this)
                    //BasicCoroutineScreen(modifier = Modifier.padding(innerPadding), viewModel, this)

                }
            }
        }
    }
}

