package ru.kotlix.frame.gateway.controller

import feign.FeignException
import org.springframework.http.HttpStatusCode
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
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
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return directoryApi.getAllDirectories(userInfo.id, communityId).map { it.toApi() }
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @GetMapping("/directory/{id}")
    override fun getDirectoryById(
        @PathVariable("id") id: Long,
    ): GatewayDirectoryDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return directoryApi.getDirectoryById(userInfo.id, id).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @PostMapping("/community/{communityId}/directory")
    override fun createDirectory(
        @PathVariable("communityId") communityId: Long,
        @RequestBody request: GatewayCreateDirectoryRequest,
    ): GatewayDirectoryDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return directoryApi.createDirectory(userInfo.id, communityId, request.toDto()).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @PutMapping("/directory/{id}")
    override fun updateDirectory(
        @PathVariable("id") id: Long,
        @RequestBody request: GatewayUpdateDirectoryRequest,
    ): GatewayDirectoryDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return directoryApi.updateDirectory(userInfo.id, id, request.toDto()).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @DeleteMapping("/directory/{id}")
    override fun deleteDirectory(
        @PathVariable("id") id: Long,
    ) {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return directoryApi.deleteDirectory(userInfo.id, id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }
}
