package ru.kotlix.frame.gateway.api.dto.entities

class GatewayRoleDto(
    val id: Long,
    val communityId: Long,
    val roleName: String,
    val priority: Int,
    val rights: Map<String, Boolean>,
)
