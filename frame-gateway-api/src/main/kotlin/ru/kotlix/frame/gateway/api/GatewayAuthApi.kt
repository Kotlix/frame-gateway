package ru.kotlix.frame.gateway.api

import ru.kotlix.frame.gateway.api.dto.requests.GatewayBasicLoginRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayBasicRegisterRequest

interface GatewayAuthApi {
    fun basicLogin(request: GatewayBasicLoginRequest): String

    fun basicRegister(request: GatewayBasicRegisterRequest)

    fun verifyRegister(secret: String)

    fun checkAuth(token: String)
}
