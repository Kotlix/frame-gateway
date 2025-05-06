package ru.kotlix.frame.gateway.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.kotlix.frame.gateway.api.GatewayCommunityApi
import ru.kotlix.frame.gateway.api.dto.entities.GatewayCommunityDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayInviteTokenDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayMemberDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateCommunityRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateTokenRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayJoinByTokenRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateCommunityRequest
import ru.kotlix.frame.gateway.mapper.toApi
import ru.kotlix.frame.gateway.mapper.toDto
import ru.kotlix.frame.gateway.service.dto.UserInfo
import ru.kotlix.frame.parties.client.PartiesCommunityClient

@RestController
@RequestMapping("/api/v1")
class CommunityController(
    private val communityApi: PartiesCommunityClient,
) : GatewayCommunityApi {
    @GetMapping("/community/{communityId}")
    override fun getById(
        @PathVariable
        communityId: Long,
    ): GatewayCommunityDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return communityApi.getById(userInfo.id, communityId).toApi()
    }

    @PostMapping("/community")
    override fun create(
        @RequestBody
        dto: GatewayCreateCommunityRequest,
    ): GatewayCommunityDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return communityApi.create(userInfo.id, dto.toDto()).toApi()
    }

    @PutMapping("/community/{communityId}")
    override fun update(
        @RequestBody
        request: GatewayUpdateCommunityRequest,
        @PathVariable
        communityId: Long,
    ): GatewayCommunityDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return communityApi.update(userInfo.id, communityId, request.toDto()).toApi()
    }

    @DeleteMapping("/community/{communityId}")
    override fun delete(
        @PathVariable
        communityId: Long,
    ) {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        communityApi.delete(userInfo.id, communityId)
    }

    @GetMapping("/all-communities")
    override fun findAllPublicWithFilter(
        @RequestParam(required = false)
        name: String?,
        @RequestParam
        pageOffset: Long,
        @RequestParam
        pageCount: Long,
    ): List<GatewayCommunityDto> {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return communityApi.findAllPublicWithFilter(userInfo.id, name, pageOffset, pageCount).map { it.toApi() }
    }

    @GetMapping("/my-communities")
    override fun findAllMine(): List<GatewayCommunityDto> {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return communityApi.findAllByUserId(userInfo.id).map { it.toApi() }
    }

    @GetMapping("/community-members/{communityId}")
    override fun getMembers(
        @PathVariable
        communityId: Long,
    ): List<GatewayMemberDto> {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return communityApi.getMembers(userInfo.id, communityId).map { it.toApi() }
    }

    @PostMapping("/community-join")
    override fun joinCommunity(
        @RequestParam
        communityId: Long,
    ) {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        communityApi.joinCommunity(userInfo.id, communityId)
    }

    @PostMapping("/community-leave")
    override fun leaveCommunity(
        @RequestParam
        communityId: Long,
    ) {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        communityApi.leaveCommunity(userInfo.id, communityId)
    }

    @PostMapping("/community-token/{communityId}")
    override fun createInviteToken(
        @PathVariable
        communityId: Long,
        @RequestBody
        request: GatewayCreateTokenRequest,
    ): GatewayInviteTokenDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return communityApi.createInviteToken(userInfo.id, communityId, request.toDto()).toApi()
    }

    @PostMapping("/community-join-token")
    override fun joinByInviteToken(
        @RequestBody
        request: GatewayJoinByTokenRequest,
    ) {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        communityApi.joinByInviteToken(userInfo.id, request.toDto())
    }
}
