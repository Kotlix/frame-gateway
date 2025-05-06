package ru.kotlix.frame.gateway.api

import ru.kotlix.frame.gateway.api.dto.GatewayUserState

interface GatewayUserStateApi {
    fun getUserStatus(userId: Long): GatewayUserState
}
