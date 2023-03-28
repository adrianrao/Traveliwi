package dev.adrianrao.traveliwi.home.presentation

data class HomeState(
    val searchText: String = "",
    val showDialog: Boolean = false,
    val filterSettings: HomeFilterSettings = HomeFilterSettings(),
    val filterSettingsBackup: HomeFilterSettings = HomeFilterSettings(),
    val reply: String? = null
)