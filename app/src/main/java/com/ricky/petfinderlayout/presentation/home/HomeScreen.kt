package com.ricky.petfinderlayout.presentation.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.petfinderlayout.R
import com.ricky.petfinderlayout.presentation.home.components.PetInfoItem
import com.ricky.petfinderlayout.presentation.home.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    navController: NavController,
    onEvent: (HomeEvent) -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberLazyListState()

    if (state.error.isNotBlank()) {
        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
    }

    Scaffold(topBar = {
        TopBar(onClick = { onEvent(HomeEvent.OnChangeTheme) })
    }) { paddingValues ->
        if (state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .wrapContentSize(align = Alignment.Center)
                )
            }

        } else {
            LazyColumn(
                contentPadding = paddingValues, state = scrollState
            ) {
                items(state.pets) { pet ->
                    PetInfoItem(pet = pet) {
//                        navController.navigate(Screens.DetailScreen.route + "/${pet.id}")
                    }
                }
                if (state.isLoadingMore) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .wrapContentSize(align = Alignment.Center)
                        )
                    }
                }
                item {
                    AnimatedVisibility(visible = state.loadMoreVisible) {
                        Row(horizontalArrangement = Arrangement.Center) {
                            TextButton(modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                                onClick = {
                                    onEvent(HomeEvent.OnLoadMore)

                                }) {
                                Text(text = stringResource(id = R.string.load_more))
                            }
                        }
                    }
                }
            }
        }

    }
}