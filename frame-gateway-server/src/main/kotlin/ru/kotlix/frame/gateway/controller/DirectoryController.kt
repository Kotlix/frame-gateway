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
import ru.kotlix.frame.gateway.api.GatewayDirectoryApi
import ru.kotlix.frame.gateway.api.dto.entities.GatewayDirectoryDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateDirectoryRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateDirectoryRequest
import ru.kotlix.frame.gateway.mapper.toApi
import ru.kotlix.frame.gateway.mapper.toDto
import ru.kotlix.frame.gateway.service.dto.UserInfo
import ru.kotlix.frame.parties.client.PartiesDirectoryClient

@RestController
@RequestMapping("/api/v1")
class DirectoryController(
    val directoryApi: PartiesDirectoryClient,
) : GatewayDirectoryApi {
    @GetMapping("/community/{communityId}/directory")
    override fun getAllDirectories(
        @PathVariable("communityId") communityId: Long,
    ): List<GatewayDirectoryDto> {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return directoryApi.getAllDirectories(userInfo.id, communityId).map { it.toApi() }
    }

    @GetMapping("/directory/{id}")
    override fun getDirectoryById(
        @PathVariable("id") id: Long,
    ): GatewayDirectoryDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return directoryApi.getDirectoryById(userInfo.id, id).toApi()
    }

    @PostMapping("/community/{communityId}/directory")
    override fun createDirectory(
        @PathVariable("communityId") communityId: Long,
        @RequestBody request: GatewayCreateDirectoryRequest,
    ): GatewayDirectoryDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return directoryApi.createDirectory(userInfo.id, communityId, request.toDto()).toApi()
    }

    @PutMapping("/directory/{id}")
    override fun updateDirectory(
        @PathVariable("id") id: Long,
        @RequestBody request: GatewayUpdateDirectoryRequest,
    ): GatewayDirectoryDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return directoryApi.updateDirectory(userInfo.id, id, request.toDto()).toApi()
    }

    @DeleteMapping("/directory/{id}")
    override fun deleteDirectory(
        @PathVariable("id") id: Long,
    ) {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return directoryApi.deleteDirectory(userInfo.id, id)
    }
}
