package dev.adrianrao.traveliwi.home.data.remote

import dev.adrianrao.traveliwi.home.data.remote.dto.OpenAIRequestDto
import dev.adrianrao.traveliwi.home.data.remote.dto.OpenAIResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAIApi {
    companion object{
        const val BASE_URL = "https://api.openai.com/v1/"
    }

    @POST("completions")
    suspend fun getTravelInformation(@Body body:OpenAIRequestDto) : OpenAIResponseDto
}