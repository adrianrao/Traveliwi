package dev.adrianrao.traveliwi.home.domain.repository

import dev.adrianrao.traveliwi.home.domain.model.HomeFilterSettings
import dev.adrianrao.traveliwi.home.domain.model.Place

interface HomeRepository {
    suspend fun getTravelInformation(location: String, filterSettings: HomeFilterSettings): Result<String>
    suspend fun getPopularPlaces():Result<List<Place>>
}