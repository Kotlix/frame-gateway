package ru.kotlix.frame.gateway.api.dto.entities

class GatewayDirectoryDto(
    val id: Long,
    val communityId: Long,
    val name: String,
    val directoryId: Long?,
    val order: Int,
)
