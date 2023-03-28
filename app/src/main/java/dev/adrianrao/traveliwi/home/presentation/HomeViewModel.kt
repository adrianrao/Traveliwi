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

    fun onSearch(newText: String) {
        state = state.copy(
            searchText = newText
        )
    }

    fun onFilterClick() {
        state = state.copy(
            showDialog = !state.showDialog
        )
    }

    fun onFilterDismiss() {
        state = state.copy(
            showDialog = false,
            filterSettings = HomeFilterSettings()
        )
    }

    fun onSettingsChange(action: HomeFilterDialogAction) {
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

    fun onBackPress() {
        state = state.copy(
            reply = null
        )
    }

    fun onRegionSelect(region: Region) {
        state = state.copy(
            selectedRegion = region,
            popularPlaces = if (region == Region.TODAS) state.popularPlacesBackup else state.popularPlacesBackup.filter { it.region == region }
        )
    }

    fun search() {
        viewModelScope.launch {
            repository.getTravelInformation(state.searchText, state.filterSettingsBackup).onSuccess {
                state = state.copy(
                    reply = it
                )
            }.onFailure {
                Log.e("HomeViewModel", it.message.toString())
            }
        }
    }
}