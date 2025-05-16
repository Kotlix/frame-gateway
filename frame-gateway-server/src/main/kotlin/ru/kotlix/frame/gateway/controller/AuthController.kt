package ru.kotlix.frame.gateway.controller

import feign.FeignException
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.kotlix.frame.auth.client.AuthClient
import ru.kotlix.frame.gateway.api.GatewayAuthApi
import ru.kotlix.frame.gateway.api.dto.requests.GatewayBasicLoginRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayBasicRegisterRequest
import ru.kotlix.frame.gateway.mapper.toDto

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authClient: AuthClient,
) : GatewayAuthApi {
    @PostMapping("/login")
    override fun basicLogin(
        @RequestBody request: GatewayBasicLoginRequest,
    ): String =
        try {
            authClient.basicLogin(request.toDto())
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }

    @PostMapping("/register")
    override fun basicRegister(
        @RequestBody request: GatewayBasicRegisterRequest,
    ) = try {
        authClient.basicRegister(request.toDto())
    } catch (e: FeignException) {
        throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
    }

    @GetMapping("/register-verify/{secret}")
    override fun verifyRegister(
        @PathVariable("secret") secret: String,
    ) = try {
        authClient.verifyRegister(secret)
    } catch (e: FeignException) {
        throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
    }

    @PostMapping("/check/{token}")
    override fun checkAuth(
        @PathVariable("token") token: String,
    ) = try {
        authClient.checkAuth(token)
    } catch (e: FeignException) {
        throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
    }
}
