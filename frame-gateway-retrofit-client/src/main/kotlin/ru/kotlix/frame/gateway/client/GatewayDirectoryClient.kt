package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import ru.kotlix.frame.gateway.api.dto.entities.GatewayDirectoryDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateDirectoryRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateDirectoryRequest

interface GatewayDirectoryClient {
    @GET("/api/v1/community/{communityId}/directory")
    suspend fun getAllDirectories(
        @Path("communityId") communityId: Long,
    ): Response<List<GatewayDirectoryDto>>

    @GET("/api/v1/directory/{id}")
    suspend fun getDirectoryById(
        @Path("id") id: Long,
    ): Response<GatewayDirectoryDto>

    @POST("/api/v1/community/{communityId}/directory")
    suspend fun createDirectory(
        @Path("communityId") communityId: Long,
        @Body request: GatewayCreateDirectoryRequest,
    ): Response<GatewayDirectoryDto>

    @PUT("/api/v1/directory/{id}")
    suspend fun updateDirectory(
        @Path("id") id: Long,
        @Body request: GatewayUpdateDirectoryRequest,
    ): Response<GatewayDirectoryDto>

    @DELETE("/api/v1/directory/{id}")
    suspend fun deleteDirectory(
        @Path("id") id: Long,
    ): Response<Void>
}