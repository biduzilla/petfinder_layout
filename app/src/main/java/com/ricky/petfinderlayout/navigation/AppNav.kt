package com.ricky.petfinderlayout.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ricky.petfinderlayout.presentation.details.DetailScreen
import com.ricky.petfinderlayout.presentation.details.DetailsViewModel
import com.ricky.petfinderlayout.presentation.home.HomeScreen
import com.ricky.petfinderlayout.presentation.home.HomeViewModel
import com.ricky.petfinderlayout.utils.Constants

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNav() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composableSlideHorizontally(Screens.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsState()

            HomeScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
        }

        composableSlideHorizontally(Screens.DetailScreen.route + "/{${Constants.PARAM_PET_ID}}") {
            val viewModel = hiltViewModel<DetailsViewModel>()
            val state by viewModel.state.collectAsState()

            DetailScreen(state = state, navController = navController)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableSlideHorizontally(
    route: String,
    duration: Int = 400, // 1000 - 400
    enterOffset: Int = 700, // 300 - 1000
    exitOffset: Int = -700,
    popEnterOffset: Int = -700,
    popExitOffset: Int = 700,
    screen: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { slideInHorizontally(tween(duration)) { enterOffset } },
        exitTransition = { slideOutHorizontally(tween(duration)) { exitOffset } },
        popEnterTransition = { slideInHorizontally(tween(duration)) { popEnterOffset } },
        popExitTransition = { slideOutHorizontally(tween(duration)) { popExitOffset } },
        content = { screen() }
    )
}