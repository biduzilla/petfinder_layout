package com.ricky.petfinderlayout.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ricky.petfinderlayout.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onClick: () -> Unit) {
    CenterAlignedTopAppBar(actions = {
        PetSwitch {
            onClick()
        }
    }, title = {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.saudacao),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = stringResource(id = R.string.encontrar_pet),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    })
}

@Composable
fun PetSwitch(onClick: () -> Unit) {
    val icon =
        if (isSystemInDarkTheme()) painterResource(id = R.drawable.ic_switch_on) else painterResource(
            R.drawable.ic_switch_off
        )
    IconButton(onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable { onClick.invoke() },
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}