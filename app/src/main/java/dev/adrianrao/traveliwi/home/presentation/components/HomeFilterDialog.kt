package dev.adrianrao.traveliwi.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.adrianrao.traveliwi.home.presentation.HomeFilterDialogAction
import dev.adrianrao.traveliwi.home.domain.model.HomeFilterSettings
import dev.adrianrao.traveliwi.ui.theme.DarkGreen

@Composable
fun HomeFilterDialog(
    onDismiss: () -> Unit,
    filterSettings: HomeFilterSettings,
    onAction: (HomeFilterDialogAction) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = onDismiss, modifier = modifier.fillMaxWidth(), buttons = {
        Button(
            onClick = { onAction(HomeFilterDialogAction.OnApplyClick) },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
                .defaultMinSize(minHeight = 53.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = DarkGreen,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "Aplicar")
        }
    }, text = {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Personas")
                HomeFilterIncrement(
                    number = filterSettings.people,
                    onMinus = { onAction(HomeFilterDialogAction.OnPeopleMinus) },
                    onPlus = { onAction(HomeFilterDialogAction.OnPeoplePlus) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Restaurantes")
                HomeFilterCheckbox(
                    onClick = { onAction(HomeFilterDialogAction.OnRestaurantClick) },
                    isChecked = filterSettings.restaurant
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Museos")
                HomeFilterCheckbox(
                    onClick = { onAction(HomeFilterDialogAction.OnMuseumClick) },
                    isChecked = filterSettings.museums
                )
            }
        }
    }, shape = RoundedCornerShape(30.dp))
}