package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.*
import ru.kotlix.frame.gateway.api.dto.entities.GatewayMessageDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewaySendMessageRequest

interface GatewayMessageClient {
    @POST("/api/v1/chat/{chatId}/send")
    suspend fun sendMessage(
        @Header("Authorization")
        token: String,
        @Path("chatId") chatId: Long,
        @Body request: GatewaySendMessageRequest,
    ): Response<GatewayMessageDto>

    @GET("/api/v1/chat/{chatId}/all")
    suspend fun getMessages(
        @Header("Authorization")
        token: String,
        @Path("chatId")
        chatId: Long,
        @Query("page")
        page: Long,
        @Query("size")
        size: Long,
    ): Response<List<GatewayMessageDto>>

    @GET("/api/v1/chat-message/{id}")
    suspend fun getById(
        @Header("Authorization")
        token: String,
        @Path("id") messageId: Long,
    ): Response<GatewayMessageDto>
}
