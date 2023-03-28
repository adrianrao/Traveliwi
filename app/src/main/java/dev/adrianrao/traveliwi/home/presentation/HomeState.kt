package dev.adrianrao.traveliwi.home.presentation

import dev.adrianrao.traveliwi.home.domain.model.HomeFilterSettings
import dev.adrianrao.traveliwi.home.domain.model.Region

data class HomeState(
    val searchText: String = "",
    val showDialog: Boolean = false,
    val filterSettings: HomeFilterSettings = HomeFilterSettings(),
    val filterSettingsBackup: HomeFilterSettings = HomeFilterSettings(),
    val reply: String? = null,
    val selectedRegion: Region = Region.TODAS
)