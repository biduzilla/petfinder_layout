package com.ricky.petfinderlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.ricky.petfinderlayout.data.local.DataStoreUtil
import com.ricky.petfinderlayout.navigation.AppNav
import com.ricky.petfinderlayout.ui.theme.PetFinderLayoutTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStoreUtil: DataStoreUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var darkMode by mutableStateOf(false)

        lifecycleScope.launch {
            dataStoreUtil.getTheme().collect {
                darkMode = it
            }
        }

        setContent {
            PetFinderLayoutTheme(darkTheme = darkMode) {
                AppNav()
            }
        }
    }
}
