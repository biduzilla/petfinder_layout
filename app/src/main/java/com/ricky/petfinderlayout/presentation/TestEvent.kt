package com.ricky.petfinderlayout.presentation

sealed interface TestEvent {
    data object OnToken : TestEvent
    data object OnPets : TestEvent
}