package dev.adrianrao.traveliwi.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import dev.adrianrao.traveliwi.home.domain.model.Region
import java.util.*

@Composable
fun HomePopularFilter(
    regions: List<Region>,
    selectedRegion: Region,
    selectRegion: (Region) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        regions.forEach {
            val colorText = if (it == selectedRegion) Color.Green else Color.Gray
            TextButton(onClick = { selectRegion(it) }) {
                Text(text = it.name.lowercase().capitalize(Locale.current), color = colorText)
            }
        }
    }
}