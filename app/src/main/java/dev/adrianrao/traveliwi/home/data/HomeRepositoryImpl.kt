package dev.adrianrao.traveliwi.home.data

import dev.adrianrao.traveliwi.home.data.remote.OpenAIApi
import dev.adrianrao.traveliwi.home.data.remote.dto.OpenAIRequestDto
import dev.adrianrao.traveliwi.home.domain.repository.HomeRepository
import dev.adrianrao.traveliwi.home.domain.model.HomeFilterSettings
import dev.adrianrao.traveliwi.home.domain.model.Place
import dev.adrianrao.traveliwi.home.domain.model.Region

class HomeRepositoryImpl(
    private val api: OpenAIApi
) : HomeRepository {
    override suspend fun getTravelInformation(location: String, filterSettings: HomeFilterSettings): Result<String> {
        return try {
            var places = ""
            if (filterSettings.restaurant) places = "Restaurantes, "
            if (filterSettings.museums) places = "Museos, "
            if (places.isNotBlank()) places = " y quiero visitar: ${places}"
            val request = OpenAIRequestDto(
                maxTokens = 1500,
                model = "text-davinci-003",
                prompt = "Sos una guía de viaje. Te voy a dar mi ubicación, y me vas a sugerir lugares para visitar cerca. También te voy a dar los tipo de lugares que quiero visitar. Aparte, quiero que me sugieras lugares de un tipo similar a los que te mencione que estén cerca de mi primera ubicación. Estoy en $location $places. Solo quiero los precios de cada lugar en USD. Ordenarlos por tipo de lugar. No repitas los lugares.",
                temperature = 0.7
            )
            val information = api.getTravelInformation(request)
            Result.success(information.choices.first().text)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun getPopularPlaces(): Result<List<Place>> {
        return Result.success(
            listOf(
                Place("USA", "New York", Region.AMERICA),
                Place("Argentina", "Salta", Region.AMERICA),
                Place("Espana", "Barcelona", Region.EUROPA),
                Place("Australia", "Sydney", Region.OCEANIA),
                Place("Japon", "Tokio", Region.ASIA),
                Place("Italia", "Roma", Region.EUROPA),
            )
        )
    }
}