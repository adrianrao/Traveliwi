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
                Place("USA", "New York", Region.AMERICA, "https://www.airpano.com/files/video-new-york-2015/images/image1.jpg"),
                Place("Argentina", "Salta", Region.AMERICA, "https://c1.wallpaperflare.com/preview/79/779/771/argentina-salta-hills-nature-arid.jpg"),
                Place("España", "Barcelona", Region.EUROPA, "https://www.fodors.com/wp-content/uploads/2022/03/Hero-UPDATEBarcelona-iStock-1320014700-1.jpg"),
                Place("Australia", "Sydney", Region.OCEANIA, "https://images.squarespace-cdn.com/content/v1/55ee34aae4b0bf70212ada4c/1577545161018-1F9Z9ZZQG9JO2O4WCWQX/keith-zhu-qaNcz43MeY8-unsplash+%281%29.jpg"),
                Place("Japon", "Tokio", Region.ASIA, "https://lonelyplanetes.cdnstatics2.com/sites/default/files/styles/max_1300x1300/public/fotos/japon_tokio_shibuya_shutterstock_666197236_f11photo_shutterstock.jpg"),
                Place("Italia", "Roma", Region.EUROPA, "https://static.barcelo.com/content/dam/bpt/posts/2018/6/que-hacer-en-roma-fiesta-republica-italiana-pin-and-travel-e1599636355846.jpg.bhgimg.jpg/1656412911395.jpg")
            )
        )
    }
}