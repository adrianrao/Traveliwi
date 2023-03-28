package dev.adrianrao.traveliwi.home.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.adrianrao.traveliwi.home.domain.model.Region
import dev.adrianrao.traveliwi.home.presentation.components.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    if (state.showDialog) {
        HomeFilterDialog(onDismiss = {
            viewModel.onEvent(HomeEvent.OnFilterDismiss)
        }, filterSettings = state.filterSettings, onAction = {
            viewModel.onEvent(HomeEvent.OnSettingsChange(it))
        })
    }

    BackHandler(state.reply != null) {
        viewModel.onEvent(HomeEvent.OnBackPress)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = PaddingValues(vertical = 32.dp)
    ) {
        item {
            Text(
                text = "A donde viajas?",
                fontSize = 28.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeSearchBar(
                    onSearch = {
                        viewModel.onEvent(HomeEvent.OnClickSearch)
                    },
                    placeholder = "Pais, Ciudad",
                    inputText = state.searchText,
                    onValueChange = { viewModel.onEvent(HomeEvent.OnSearch(it)) }
                )
                HomeFilterButton(onClick = { viewModel.onEvent(HomeEvent.OnFilterClick) })
            }
        }

        if (state.isLoading) {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        } else {
            state.reply?.let {
                item {
                    Text(text = it, modifier = Modifier.padding(horizontal = 16.dp))
                }
            } ?: item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Lugares Populares",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    HomePopularFilter(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        selectedRegion = state.selectedRegion,
                        selectRegion = {
                            viewModel.onEvent(HomeEvent.OnRegionSelect(it))
                        },
                        regions = Region.values().toList()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(state.popularPlaces) { place ->
                            HomePopularPlace(place = place) {
                                viewModel.onEvent(HomeEvent.OnSearch(it))
                            }
                        }
                    }
                }
            }
        }

    }
}