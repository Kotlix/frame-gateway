package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.kotlix.frame.gateway.api.dto.GatewayUserState

interface GatewayUserStateClient {
    @GET("/api/v1/status/{userId}")
    suspend fun getUserStatus(
        @Header("Authorization")
        token: String,
        @Path("userId")
        userId: Long,
    ): Response<GatewayUserState>
}
