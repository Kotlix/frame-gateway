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
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return voiceApi.getAllVoices(userInfo.id, communityId).map { it.toApi() }
    }

    @GetMapping("/voice/{id}")
    override fun getVoiceById(
        @PathVariable("id") id: Long,
    ): GatewayVoiceDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return voiceApi.getVoiceById(userInfo.id, id).toApi()
    }

    @PostMapping("/community/{communityId}/voice")
    override fun createVoice(
        @PathVariable("communityId") communityId: Long,
        @RequestBody request: GatewayCreateVoiceRequest,
    ): GatewayVoiceDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return voiceApi.createVoice(userInfo.id, communityId, request.toDto()).toApi()
    }

    @PutMapping("/voice/{id}")
    override fun updateVoice(
        @PathVariable("id") id: Long,
        @RequestBody request: GatewayUpdateVoiceRequest,
    ): GatewayVoiceDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return voiceApi.updateVoice(userInfo.id, id, request.toDto()).toApi()
    }

    @DeleteMapping("/voice/{id}")
    override fun deleteVoice(
        @PathVariable("id") id: Long,
    ) {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return voiceApi.deleteVoice(userInfo.id, id)
    }

    @PostMapping("/voice-join")
    override fun joinVoice(
        @RequestParam id: Long,
        @RequestParam userId: Long,
    ): GatewayConnectionGuide {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return voiceApi.joinVoice(userInfo.id, id, userId).toApi()
    }

    @PostMapping("/voice-leave")
    override fun leaveVoice(
        @RequestParam id: Long,
        @RequestParam userId: Long,
    ) {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return voiceApi.leaveVoice(userInfo.id, id, userId)
    }
}
