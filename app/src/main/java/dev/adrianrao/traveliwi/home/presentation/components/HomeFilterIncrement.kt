package dev.adrianrao.traveliwi.home.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeFilterIncrement(
    number: Int,
    onMinus: () -> Unit,
    onPlus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        HomeFilterSettingsButton(
            onMinus,
            Icons.Default.Remove
        )
        Text(text = "$number")
        HomeFilterSettingsButton(
            onPlus,
            Icons.Default.Add
        )
    }
}