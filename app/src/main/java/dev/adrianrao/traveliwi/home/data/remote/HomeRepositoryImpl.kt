package dev.adrianrao.traveliwi.home.data.remote

import dev.adrianrao.traveliwi.home.data.remote.dto.OpenAIRequestDto
import dev.adrianrao.traveliwi.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val api : OpenAIApi
) : HomeRepository {
    override suspend fun getTravelInformation(): Result<String> {
        return try{
            val request = OpenAIRequestDto(
                maxTokens = 1500,
                model = "",
                prompt = "",
                temperature = 0.7
            )
            val information = api.getTravelInformation(request)
            Result.success(information.choices.first().text)
        }catch(ex:Exception){
            Result.failure(ex)
        }
    }
}