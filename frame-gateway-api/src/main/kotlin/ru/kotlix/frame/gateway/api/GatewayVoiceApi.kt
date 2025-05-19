package ru.kotlix.frame.gateway.api

import ru.kotlix.frame.gateway.api.dto.entities.GatewayConnectionGuide
import ru.kotlix.frame.gateway.api.dto.entities.GatewayVoiceDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateVoiceRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateVoiceRequest

interface GatewayVoiceApi {
    fun getAllVoices(communityId: Long): List<GatewayVoiceDto>

    fun getVoiceById(id: Long): GatewayVoiceDto

    fun createVoice(
        communityId: Long,
        request: GatewayCreateVoiceRequest,
    ): GatewayVoiceDto

    fun updateVoice(
        id: Long,
        request: GatewayUpdateVoiceRequest,
    ): GatewayVoiceDto

    fun deleteVoice(id: Long)

    fun joinVoice(id: Long): GatewayConnectionGuide

    fun leaveVoice(id: Long)

    fun getVoiceUsers(id: Long): List<Long>
}
