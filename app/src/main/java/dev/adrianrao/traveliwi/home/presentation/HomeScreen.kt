package dev.adrianrao.traveliwi.home.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.adrianrao.traveliwi.home.domain.model.Region
import dev.adrianrao.traveliwi.home.presentation.components.HomeFilterButton
import dev.adrianrao.traveliwi.home.presentation.components.HomeFilterDialog
import dev.adrianrao.traveliwi.home.presentation.components.HomePopularFilter
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

    BackHandler(state.reply != null) {
        viewModel.onBackPress()
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
        state.reply?.let {
            Text(modifier = Modifier.verticalScroll(rememberScrollState()), text = it)
        } ?: Column(modifier = Modifier.fillMaxWidth()) {
            if (state.popularPlaces.isNotEmpty()) {
                Text(text = "Lugares Populares")
                HomePopularFilter(
                    modifier = Modifier.fillMaxWidth(),
                    regions = Region.values().toList(),
                    selectRegion = {
                        viewModel.onRegionSelect(it)
                    },
                    selectedRegion = state.selectedRegion
                )
                LazyRow(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    items(state.popularPlaces) {
                        TextButton(onClick = {
                            viewModel.onSearch("${it.country}, ${it.city}")
                        }) {
                            Text(text = "${it.country}, ${it.city}")
                        }
                    }
                }
            }
        }
    }
}