package ru.kotlix.frame.gateway.api

import ru.kotlix.frame.gateway.api.dto.entities.GatewayMessageDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewaySendMessageRequest

interface GatewayMessageApi {
    fun sendMessage(
        chatId: Long,
        request: GatewaySendMessageRequest,
    ): GatewayMessageDto

    fun getMessages(
        chatId: Long,
        page: Long,
        size: Long,
    ): List<GatewayMessageDto>

    fun getById(messageId: Long): GatewayMessageDto
}
