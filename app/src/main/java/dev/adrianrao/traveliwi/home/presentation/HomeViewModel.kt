package dev.adrianrao.traveliwi.home.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adrianrao.traveliwi.home.domain.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

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

    fun search() {
        viewModelScope.launch {
            repository.getTravelInformation(state.searchText,state.filterSettingsBackup).onSuccess {
                Log.d("HomeViewModel", it)
            }.onFailure {
                Log.e("HomeViewModel", it.message.toString())
            }
        }
    }
}