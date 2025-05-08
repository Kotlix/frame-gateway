package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.GET

interface GatewayServerClient {
    @GET("/api/v1/voice/servers")
    suspend fun getServers(): Response<Map<String, List<String>>>
}