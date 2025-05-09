package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GatewayServerClient {
    @GET("/api/v1/voice/servers")
    suspend fun getServers(
        @Header("Authorization")
        token: String,
    ): Response<Map<String, List<String>>>
}
