package dev.adrianrao.traveliwi.home.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adrianrao.traveliwi.home.domain.model.HomeFilterSettings
import dev.adrianrao.traveliwi.home.domain.model.Region
import dev.adrianrao.traveliwi.home.domain.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            repository.getPopularPlaces().onSuccess {
                state = state.copy(
                    popularPlaces = it,
                    popularPlacesBackup = it
                )
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnBackPress -> onBackPress()
            HomeEvent.OnClickSearch -> search()
            HomeEvent.OnFilterClick -> onFilterClick()
            HomeEvent.OnFilterDismiss -> onFilterDismiss()
            is HomeEvent.OnRegionSelect -> {
                onRegionSelect(event.region)
            }

            is HomeEvent.OnSearch -> {
                onSearch(event.newText)
            }

            is HomeEvent.OnSettingsChange -> {
                onSettingsChange(event.action)
            }
        }
    }

    private fun onSearch(newText: String) {
        state = state.copy(
            searchText = newText
        )
    }

    private fun onFilterClick() {
        state = state.copy(
            showDialog = !state.showDialog
        )
    }

    private fun onFilterDismiss() {
        state = state.copy(
            showDialog = false,
            filterSettings = HomeFilterSettings()
        )
    }

    private fun onSettingsChange(action: HomeFilterDialogAction) {
        when (action) {

            HomeFilterDialogAction.OnApplyClick -> {
                state = state.copy(
                    filterSettingsBackup = state.filterSettings,
                    showDialog = false
                )
            }

            HomeFilterDialogAction.OnMuseumClick -> {
                state = state.copy(
                    filterSettings = state.filterSettings.copy(
                        museums = !state.filterSettings.museums
                    )
                )
            }

            HomeFilterDialogAction.OnPeopleMinus -> {
                state = state.copy(
                    filterSettings = state.filterSettings.copy(
                        people = state.filterSettings.people - 1
                    )
                )
            }

            HomeFilterDialogAction.OnPeoplePlus -> {
                state = state.copy(
                    filterSettings = state.filterSettings.copy(
                        people = state.filterSettings.people + 1
                    )
                )
            }

            HomeFilterDialogAction.OnRestaurantClick -> {
                state = state.copy(
                    filterSettings = state.filterSettings.copy(
                        restaurant = !state.filterSettings.restaurant
                    )
                )
            }

        }
    }

    private fun onBackPress() {
        state = state.copy(
            reply = null
        )
    }

    private fun onRegionSelect(region: Region) {
        state = state.copy(
            selectedRegion = region,
            popularPlaces = if (region == Region.TODAS) state.popularPlacesBackup else state.popularPlacesBackup.filter { it.region == region }
        )
    }

    private fun search() {
        viewModelScope.launch {

            state = state.copy(
                isLoading = true
            )

            repository.getTravelInformation(state.searchText, state.filterSettingsBackup).onSuccess {
                state = state.copy(
                    reply = it,
                    isLoading = false
                )
            }.onFailure {
                Log.e("HomeViewModel", it.message.toString())
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }
}