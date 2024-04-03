package com.ricky.petfinderlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.ricky.petfinderlayout.presentation.TestScreen
import com.ricky.petfinderlayout.presentation.TestViewModel
import com.ricky.petfinderlayout.ui.theme.PetFinderLayoutTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetFinderLayoutTheme {
                val viewModel = hiltViewModel<TestViewModel>()
                TestScreen(onEvent = viewModel::onEvent)
            }
        }
    }
}
