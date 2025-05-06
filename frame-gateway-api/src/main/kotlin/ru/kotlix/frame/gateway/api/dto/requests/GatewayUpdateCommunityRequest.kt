package ru.kotlix.frame.gateway.api.dto.requests

data class GatewayUpdateCommunityRequest(
    val name: String,
    val desc: String?,
    val isPublic: Boolean,
    val voiceRegion: String,
    val voiceName: String,
)
