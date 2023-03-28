package dev.adrianrao.traveliwi.home.domain.repository

import dev.adrianrao.traveliwi.home.presentation.HomeFilterSettings

interface HomeRepository {
    suspend fun getTravelInformation(location: String, filterSettings: HomeFilterSettings): Result<String>
}