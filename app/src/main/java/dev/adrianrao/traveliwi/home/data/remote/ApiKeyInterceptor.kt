package dev.adrianrao.traveliwi.home.data.remote

import dev.adrianrao.traveliwi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization","Bearer ${BuildConfig.API_KEY}")
        return chain.proceed(request.build())
    }
}