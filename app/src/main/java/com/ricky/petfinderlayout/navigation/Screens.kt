package com.ricky.petfinderlayout.navigation

sealed class Screens(val route: String) {
    data object HomeScreen : Screens(
        route = "home_screen"
    )

    data object DetailScreen : Screens(
        route = "detail_screen"
    )
}
