package ru.kotlix.frame.gateway.api.dto.requests

data class GatewayUpdateRoleRequest(
    val roleName: String,
    val priority: Int,
    val rights: Map<String, Boolean>,
)
