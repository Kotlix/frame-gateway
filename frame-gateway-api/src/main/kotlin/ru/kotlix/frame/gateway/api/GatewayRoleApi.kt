package ru.kotlix.frame.gateway.api

import ru.kotlix.frame.gateway.api.dto.entities.GatewayRoleDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateRoleRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateRoleRequest

interface GatewayRoleApi {
    fun getAllRoles(communityId: Long): List<GatewayRoleDto>

    fun createRole(
        communityId: Long,
        request: GatewayCreateRoleRequest,
    ): GatewayRoleDto

    fun getRole(id: Long): GatewayRoleDto

    fun updateRole(
        id: Long,
        request: GatewayUpdateRoleRequest,
    ): GatewayRoleDto

    fun deleteRole(id: Long)

    fun assignRole(
        targetId: Long,
        id: Long,
    )

    fun unassignRole(
        targetId: Long,
        id: Long,
    )
}
