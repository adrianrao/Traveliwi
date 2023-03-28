package dev.adrianrao.traveliwi.home.presentation

import dev.adrianrao.traveliwi.home.domain.model.Region

sealed class HomeEvent {
    data class OnSearch(val newText: String) : HomeEvent()
    data class OnSettingsChange(val action: HomeFilterDialogAction) : HomeEvent()
    data class OnRegionSelect(val region: Region) : HomeEvent()
    object OnFilterClick : HomeEvent()
    object OnFilterDismiss : HomeEvent()
    object OnBackPress : HomeEvent()
    object OnClickSearch : HomeEvent()
}