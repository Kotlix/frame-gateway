package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.*
import ru.kotlix.frame.gateway.api.dto.entities.GatewayConnectionGuide
import ru.kotlix.frame.gateway.api.dto.entities.GatewayVoiceDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateVoiceRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateVoiceRequest

interface GatewayVoiceClient {
    @GET("/api/v1/community/{communityId}/voice")
    suspend fun getAllVoices(
        @Header("Authorization")
        token: String,
        @Path("communityId")
        communityId: Long,
    ): Response<List<GatewayVoiceDto>>

    @GET("/api/v1/voice/{id}")
    suspend fun getVoiceById(
        @Header("Authorization")
        token: String,
        @Path("id")
        id: Long,
    ): Response<GatewayVoiceDto>

    @POST("/api/v1/community/{communityId}/voice")
    suspend fun createVoice(
        @Header("Authorization")
        token: String,
        @Path("communityId")
        communityId: Long,
        @Body
        request: GatewayCreateVoiceRequest,
    ): Response<GatewayVoiceDto>

    @PUT("/api/v1/voice/{id}")
    suspend fun updateVoice(
        @Header("Authorization")
        token: String,
        @Path("id")
        id: Long,
        @Body
        request: GatewayUpdateVoiceRequest,
    ): Response<GatewayVoiceDto>

    @DELETE("/api/v1/voice/{id}")
    suspend fun deleteVoice(
        @Header("Authorization")
        token: String,
        @Path("id")
        id: Long,
    ): Response<Void>

    @POST("/api/v1/voice/{id}/join")
    suspend fun joinVoice(
        @Header("Authorization")
        token: String,
        @Path("id")
        id: Long,
    ): Response<GatewayConnectionGuide>

    @POST("/api/v1/voice/{id}/leave")
    suspend fun leaveVoice(
        @Header("Authorization")
        token: String,
        @Path("id")
        id: Long,
    ): Response<Void>
}
