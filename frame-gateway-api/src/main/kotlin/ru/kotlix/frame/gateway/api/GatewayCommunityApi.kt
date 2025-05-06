package ru.kotlix.frame.gateway.api

import ru.kotlix.frame.gateway.api.dto.entities.GatewayCommunityDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayInviteTokenDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayMemberDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateCommunityRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateTokenRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayJoinByTokenRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateCommunityRequest

interface GatewayCommunityApi {
    fun getById(communityId: Long): GatewayCommunityDto

    fun create(dto: GatewayCreateCommunityRequest): GatewayCommunityDto

    fun update(
        request: GatewayUpdateCommunityRequest,
        communityId: Long,
    ): GatewayCommunityDto

    fun delete(communityId: Long)

    fun findAllPublicWithFilter(
        name: String?,
        pageOffset: Long,
        pageCount: Long,
    ): List<GatewayCommunityDto>

    fun findAllMine(): List<GatewayCommunityDto>

    fun getMembers(communityId: Long): List<GatewayMemberDto>

    fun joinCommunity(communityId: Long)

    fun leaveCommunity(communityId: Long)

    fun createInviteToken(
        communityId: Long,
        request: GatewayCreateTokenRequest,
    ): GatewayInviteTokenDto

    fun joinByInviteToken(request: GatewayJoinByTokenRequest)
}
