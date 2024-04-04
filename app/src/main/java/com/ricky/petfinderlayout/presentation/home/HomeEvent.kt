package com.ricky.petfinderlayout.presentation.home

sealed interface HomeEvent {
    data object OnChangeTheme : HomeEvent
    data object OnLoadMore : HomeEvent
}