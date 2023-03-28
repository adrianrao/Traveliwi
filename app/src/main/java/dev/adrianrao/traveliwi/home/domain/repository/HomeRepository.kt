package dev.adrianrao.traveliwi.home.domain.repository

interface HomeRepository {
    suspend fun getTravelInformation() : Result<String>
}