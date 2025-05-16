package ru.kotlix.frame.gateway.controller

import feign.FeignException
import org.springframework.http.HttpStatusCode
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.kotlix.frame.auth.client.AuthClient
import ru.kotlix.frame.gateway.api.GatewayProfileApi
import ru.kotlix.frame.gateway.api.dto.entities.GatewayFullProfileInfoDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayProfileInfoDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangeEmailRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangePasswordRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangeUsernameRequest
import ru.kotlix.frame.gateway.mapper.toApi
import ru.kotlix.frame.gateway.mapper.toDto
import ru.kotlix.frame.gateway.service.dto.UserInfo

@RestController("kotlixProfileController")
@RequestMapping("/api/v1/profile")
class ProfileController(
    private val authClient: AuthClient,
) : GatewayProfileApi {
    @PostMapping("/email")
    override fun changeEmail(
        @RequestBody request: GatewayChangeEmailRequest,
    ) {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            authClient.changeEmail(userInfo.id, request.toDto())
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @GetMapping("/email-verify/{secret}")
    override fun changeEmailApply(
        @PathVariable("secret") secret: String,
    ) {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            authClient.changeEmailApply(userInfo.id, secret)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @PostMapping("/username")
    override fun changeUsername(
        @RequestBody request: GatewayChangeUsernameRequest,
    ) {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            authClient.changeUsername(userInfo.id, request.toDto())
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @GetMapping("/username-verify/{secret}")
    override fun changeUsernameApply(
        @PathVariable("secret") secret: String,
    ) {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            authClient.changeUsernameApply(userInfo.id, secret)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @PostMapping("/password")
    override fun changePassword(
        @RequestBody request: GatewayChangePasswordRequest,
    ) {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            authClient.changePassword(userInfo.id, request.toDto())
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @GetMapping("/password-verify/{secret}")
    override fun changePasswordApply(
        @PathVariable("secret") secret: String,
    ) {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            authClient.changePasswordApply(userInfo.id, secret)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @GetMapping("/info")
    override fun getMyProfileInfo(): GatewayFullProfileInfoDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return authClient.getMyProfileInfo(userInfo.id).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @GetMapping("/info/{userId}")
    override fun getProfileInfo(
        @PathVariable("userId") userId: Long,
    ): GatewayProfileInfoDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return authClient.getProfileInfo(userInfo.id, userId).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }
}
