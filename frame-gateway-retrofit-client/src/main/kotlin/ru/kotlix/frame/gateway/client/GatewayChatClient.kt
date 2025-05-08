package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import ru.kotlix.frame.gateway.api.dto.entities.GatewayChatDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateChatRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateChatRequest

interface GatewayChatClient {
    @GET("/api/v1/community/{communityId}/chat")
    suspend fun getAllChats(
        @Path("communityId")
        communityId: Long,
    ): Response<List<GatewayChatDto>>

    @GET("/api/v1/chat/{id}")
    suspend fun getChatById(
        @Path("id")
        id: Long,
    ): Response<GatewayChatDto>

    @POST("/api/v1/community/{communityId}/chat")
    suspend fun createChat(
        @Path("communityId")
        communityId: Long,
        @Body
        request: GatewayCreateChatRequest,
    ): Response<GatewayChatDto>

    @PUT("/api/v1/chat/{id}")
    suspend fun updateChat(
        @Path("id")
        id: Long,
        @Body
        request: GatewayUpdateChatRequest,
    ): Response<GatewayChatDto>

    @DELETE("/api/v1/chat/{id}")
    suspend fun deleteChat(
        @Path("id")
        id: Long,
    ): Response<Void>
}
