package ru.kotlix.frame.gateway.api

import ru.kotlix.frame.gateway.api.dto.entities.GatewayFullProfileInfoDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayProfileInfoDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangeEmailRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangePasswordRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangeUsernameRequest

interface GatewayProfileApi {
    fun changeEmail(request: GatewayChangeEmailRequest)

    fun changeEmailApply(secret: String)

    fun changeUsername(request: GatewayChangeUsernameRequest)

    fun changeUsernameApply(secret: String)

    fun changePassword(request: GatewayChangePasswordRequest)

    fun changePasswordApply(secret: String)

    fun getMyProfileInfo(): GatewayFullProfileInfoDto

    fun getProfileInfo(userId: Long): GatewayProfileInfoDto
}
