package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.kotlix.frame.gateway.api.dto.requests.GatewayBasicLoginRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayBasicRegisterRequest

interface GatewayAuthClient {
    @POST("/api/v1/auth/login")
    suspend fun basicLogin(
        @Body
        request: GatewayBasicLoginRequest,
    ): Response<String>

    @POST("/api/v1/auth/register")
    suspend fun basicRegister(
        @Body
        request: GatewayBasicRegisterRequest,
    ): Response<Void>

    @GET("/api/v1/auth/register-verify/{secret}")
    suspend fun verifyRegister(
        @Path("secret")
        secret: String,
    ): Response<Void>

    @POST("/api/v1/auth/check/{token}")
    suspend fun checkAuth(
        @Path("token")
        token: String,
    ): Response<Void>
}
