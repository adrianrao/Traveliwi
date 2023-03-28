package dev.adrianrao.traveliwi.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.adrianrao.traveliwi.ui.theme.FilterColor
import dev.adrianrao.traveliwi.ui.theme.FilterGray

@Composable
fun HomeFilterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.background(color = FilterGray, shape = RoundedCornerShape(13.dp))
    ) {
        Icon(imageVector = Icons.Default.Tune, contentDescription = "filter", tint = FilterColor)
    }
}