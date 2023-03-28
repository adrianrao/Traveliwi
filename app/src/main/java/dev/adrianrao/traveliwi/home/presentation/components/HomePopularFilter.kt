package dev.adrianrao.traveliwi.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import dev.adrianrao.traveliwi.home.domain.model.Region
import dev.adrianrao.traveliwi.ui.theme.DarkGreen
import java.util.*

@Composable
fun HomePopularFilter(
    selectedRegion: Region,
    selectRegion: (Region) -> Unit,
    modifier: Modifier = Modifier,
    regions: List<Region>
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        regions.forEach {
            val textColor = if (it == selectedRegion) DarkGreen else Color.Gray
            TextButton(
                onClick = { selectRegion(it) },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = DarkGreen
                )
            ) {
                Text(text = it.name.lowercase().capitalize(Locale.current), color = textColor)
            }
        }
    }
}