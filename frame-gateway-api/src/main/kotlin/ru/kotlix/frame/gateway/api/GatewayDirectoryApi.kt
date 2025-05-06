package ru.kotlix.frame.gateway.api

import ru.kotlix.frame.gateway.api.dto.entities.GatewayDirectoryDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateDirectoryRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateDirectoryRequest

interface GatewayDirectoryApi {
    fun getAllDirectories(communityId: Long): List<GatewayDirectoryDto>

    fun getDirectoryById(id: Long): GatewayDirectoryDto

    fun createDirectory(
        communityId: Long,
        request: GatewayCreateDirectoryRequest,
    ): GatewayDirectoryDto

    fun updateDirectory(
        id: Long,
        request: GatewayUpdateDirectoryRequest,
    ): GatewayDirectoryDto

    fun deleteDirectory(id: Long)
}
