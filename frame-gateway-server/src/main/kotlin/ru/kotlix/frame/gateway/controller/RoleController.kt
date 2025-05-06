package ru.kotlix.frame.gateway.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kotlix.frame.gateway.api.GatewayRoleApi
import ru.kotlix.frame.gateway.api.dto.entities.GatewayRoleDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateRoleRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateRoleRequest
import ru.kotlix.frame.gateway.mapper.toApi
import ru.kotlix.frame.gateway.mapper.toDto
import ru.kotlix.frame.gateway.service.dto.UserInfo
import ru.kotlix.frame.parties.client.PartiesRoleClient

@RestController
@RequestMapping("/api/v1")
class RoleController(
    val roleApi: PartiesRoleClient,
) : GatewayRoleApi {
    @GetMapping("/community/{communityId}/role")
    override fun getAllRoles(
        @PathVariable("communityId") communityId: Long,
    ): List<GatewayRoleDto> {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return roleApi.getAllRoles(userInfo.id, communityId).map { it.toApi() }
    }

    @PostMapping("/community/{communityId}/role")
    override fun createRole(
        @PathVariable("communityId") communityId: Long,
        @RequestBody request: GatewayCreateRoleRequest,
    ): GatewayRoleDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return roleApi.createRole(userInfo.id, communityId, request.toDto()).toApi()
    }

    @GetMapping("/role/{id}")
    override fun getRole(
        @PathVariable("id") id: Long,
    ): GatewayRoleDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return roleApi.getRole(userInfo.id, id).toApi()
    }

    @PutMapping("/role/{id}")
    override fun updateRole(
        @PathVariable("id") id: Long,
        @RequestBody request: GatewayUpdateRoleRequest,
    ): GatewayRoleDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return roleApi.updateRole(userInfo.id, id, request.toDto()).toApi()
    }

    @DeleteMapping("/role/{id}")
    override fun deleteRole(
        @PathVariable("id") id: Long,
    ) {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return roleApi.deleteRole(userInfo.id, id)
    }
}
