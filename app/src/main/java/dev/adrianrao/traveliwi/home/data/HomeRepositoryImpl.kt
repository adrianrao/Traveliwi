package dev.adrianrao.traveliwi.home.data

import dev.adrianrao.traveliwi.home.data.remote.OpenAIApi
import dev.adrianrao.traveliwi.home.data.remote.dto.OpenAIRequestDto
import dev.adrianrao.traveliwi.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val api: OpenAIApi
) : HomeRepository {
    override suspend fun getTravelInformation(): Result<String> {
        return try {
            val request = OpenAIRequestDto(
                maxTokens = 1500,
                model = "text-davinci-003",
                prompt = "Sos una guía de viaje. Te voy a dar mi ubicación, y me vas a sugerir lugares para visitar cerca. También te voy a dar los tipo de lugares que quiero visitar. Aparte, quiero que me sugieras lugares de un tipo similar a los que te mencione que estén cerca de mi primera ubicación. Estoy en Buenos Aires, Argentina y quiero visitar: Museos, Restaurantes. Dame los precios de cada lugar en USD. Ordenarlos por tipo de lugar.",
                temperature = 0.7
            )
            val information = api.getTravelInformation(request)
            Result.success(information.choices.first().text)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}