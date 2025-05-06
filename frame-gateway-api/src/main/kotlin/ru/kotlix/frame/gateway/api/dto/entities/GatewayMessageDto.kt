package ru.kotlix.frame.gateway.api.dto.entities

import java.time.LocalDateTime

class GatewayMessageDto(
    val id: Long,
    val chatId: Long,
    val authorId: Long,
    val message: String,
    val createdAt: LocalDateTime,
)
