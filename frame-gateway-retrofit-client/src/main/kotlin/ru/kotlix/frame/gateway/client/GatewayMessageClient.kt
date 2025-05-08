package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kotlix.frame.gateway.api.dto.entities.GatewayMessageDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewaySendMessageRequest

interface GatewayMessageClient {
    @POST("/api/v1/chat/{chatId}/send")
    suspend fun sendMessage(
        @Path("chatId") chatId: Long,
        @Body request: GatewaySendMessageRequest,
    ): Response<GatewayMessageDto>

    @GET("/api/v1/chat/{chatId}/all")
    suspend fun getMessages(
        @Path("chatId")
        chatId: Long,
        @Query("page")
        page: Long,
        @Query("size")
        size: Long,
    ): Response<List<GatewayMessageDto>>

    @GET("/api/v1/chat-message/{id}")
    suspend fun getById(
        @Path("id") messageId: Long,
    ): Response<GatewayMessageDto>
}
