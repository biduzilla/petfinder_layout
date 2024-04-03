package com.ricky.petfinderlayout

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ricky.petfinderlayout.domain.use_cases.GetToken
import com.ricky.petfinderlayout.presentation.TestEvent
import com.ricky.petfinderlayout.presentation.TestScreen
import com.ricky.petfinderlayout.presentation.TestViewModel
import com.ricky.petfinderlayout.ui.theme.PetFinderLayoutTheme
import com.ricky.petfinderlayout.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var getToken: GetToken
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
