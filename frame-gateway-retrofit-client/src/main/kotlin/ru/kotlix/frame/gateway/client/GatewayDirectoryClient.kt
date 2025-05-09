package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.*
import ru.kotlix.frame.gateway.api.dto.entities.GatewayDirectoryDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateDirectoryRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateDirectoryRequest

interface GatewayDirectoryClient {
    @GET("/api/v1/community/{communityId}/directory")
    suspend fun getAllDirectories(
        @Header("Authorization")
        token: String,
        @Path("communityId") communityId: Long,
    ): Response<List<GatewayDirectoryDto>>

    @GET("/api/v1/directory/{id}")
    suspend fun getDirectoryById(
        @Header("Authorization")
        token: String,
        @Path("id") id: Long,
    ): Response<GatewayDirectoryDto>

    @POST("/api/v1/community/{communityId}/directory")
    suspend fun createDirectory(
        @Header("Authorization")
        token: String,
        @Path("communityId") communityId: Long,
        @Body request: GatewayCreateDirectoryRequest,
    ): Response<GatewayDirectoryDto>

    @PUT("/api/v1/directory/{id}")
    suspend fun updateDirectory(
        @Header("Authorization")
        token: String,
        @Path("id") id: Long,
        @Body request: GatewayUpdateDirectoryRequest,
    ): Response<GatewayDirectoryDto>

    @DELETE("/api/v1/directory/{id}")
    suspend fun deleteDirectory(
        @Header("Authorization")
        token: String,
        @Path("id") id: Long,
    ): Response<Void>
}
