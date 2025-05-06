package ru.kotlix.frame.gateway.api

import ru.kotlix.frame.gateway.api.dto.entities.GatewayChatDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateChatRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateChatRequest

interface GatewayChatApi {
    fun getAllChats(communityId: Long): List<GatewayChatDto>

    fun getChatById(id: Long): GatewayChatDto

    fun createChat(
        communityId: Long,
        request: GatewayCreateChatRequest,
    ): GatewayChatDto

    fun updateChat(
        id: Long,
        request: GatewayUpdateChatRequest,
    ): GatewayChatDto

    fun deleteChat(id: Long)
}
