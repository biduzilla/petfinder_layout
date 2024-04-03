package com.ricky.petfinderlayout.presentation

sealed interface TestEvent {
    data object OnClick : TestEvent
}