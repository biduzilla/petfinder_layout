package com.ricky.petfinderlayout.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TestScreen(onEvent: (TestEvent) -> Unit) {
    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                onEvent(TestEvent.OnToken)
            }) {
                Text(text = "Token")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                onEvent(TestEvent.OnPets)
            }) {
                Text(text = "Pets")
            }
        }
    }
}