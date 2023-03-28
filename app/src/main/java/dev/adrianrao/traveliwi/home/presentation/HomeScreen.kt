package dev.adrianrao.traveliwi.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.adrianrao.traveliwi.home.presentation.components.HomeFilterButton
import dev.adrianrao.traveliwi.home.presentation.components.HomeFilterDialog
import dev.adrianrao.traveliwi.home.presentation.components.HomeSearchBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    if (state.showDialog) {
        HomeFilterDialog(
            onDismiss = {
                viewModel.onFilterDismiss()
            },
            onAction = {
                viewModel.onSettingsChange(it)
            },
            filterSettings = state.filterSettings
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "A donde viajas?")
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeSearchBar(
                onSearch = { viewModel.search() },
                onValueChange = { viewModel.onSearch(it) },
                placeholder = "Pais,Ciudad",
                inputText = state.searchText,
            )
            HomeFilterButton(onClick = { viewModel.onFilterClick() })
        }
    }
}