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
import ru.kotlix.frame.gateway.api.GatewayVoiceApi
import ru.kotlix.frame.gateway.api.dto.entities.GatewayConnectionGuide
import ru.kotlix.frame.gateway.api.dto.entities.GatewayVoiceDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateVoiceRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateVoiceRequest
import ru.kotlix.frame.gateway.mapper.toApi
import ru.kotlix.frame.gateway.mapper.toDto
import ru.kotlix.frame.gateway.service.dto.UserInfo
import ru.kotlix.frame.parties.client.PartiesVoiceClient

@RestController
@RequestMapping("/api/v1")
class VoiceController(
    val voiceApi: PartiesVoiceClient,
) : GatewayVoiceApi {
    @GetMapping("/community/{communityId}/voice")
    override fun getAllVoices(
        @PathVariable("communityId") communityId: Long,
    ): List<GatewayVoiceDto> {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return voiceApi.getAllVoices(userInfo.id, communityId).map { it.toApi() } 
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @GetMapping("/voice/{id}")
    override fun getVoiceById(
        @PathVariable("id") id: Long,
    ): GatewayVoiceDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return voiceApi.getVoiceById(userInfo.id, id).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @PostMapping("/community/{communityId}/voice")
    override fun createVoice(
        @PathVariable("communityId") communityId: Long,
        @RequestBody request: GatewayCreateVoiceRequest,
    ): GatewayVoiceDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return voiceApi.createVoice(userInfo.id, communityId, request.toDto()).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @PutMapping("/voice/{id}")
    override fun updateVoice(
        @PathVariable("id") id: Long,
        @RequestBody request: GatewayUpdateVoiceRequest,
    ): GatewayVoiceDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return voiceApi.updateVoice(userInfo.id, id, request.toDto()).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @DeleteMapping("/voice/{id}")
    override fun deleteVoice(
        @PathVariable("id") id: Long,
    ) {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return voiceApi.deleteVoice(userInfo.id, id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @PostMapping("/voice/{id}/join")
    override fun joinVoice(
        @PathVariable("id")
        id: Long,
    ): GatewayConnectionGuide {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return voiceApi.joinVoice(userInfo.id, id).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @PostMapping("/voice/{id}/leave")
    override fun leaveVoice(
        @PathVariable("id")
        id: Long,
    ) {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return voiceApi.leaveVoice(userInfo.id, id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }
}
