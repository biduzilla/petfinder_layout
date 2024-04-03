package com.ricky.petfinderlayout

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import com.ricky.petfinderlayout.domain.use_cases.GetToken
import com.ricky.petfinderlayout.ui.theme.PetFinderLayoutTheme
import com.ricky.petfinderlayout.utils.Resource
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    private lateinit var getToken: GetToken
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetFinderLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Test {
                        getToken().onEach { result ->
                            when (result) {
                                is Resource.Error -> Log.i("infoteste", "Error: ${result.message}")
                                is Resource.Loading -> Log.i("infoteste", "Loading")
                                is Resource.Success -> Log.i("infoteste", "Sucess: ${result.data}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Test(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onClick) {
                Text(text = "Click")
            }
        }
    }
}