package ru.kotlix.frame.gateway.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
    ): String = authClient.basicLogin(request.toDto())

    @PostMapping("/register")
    override fun basicRegister(
        @RequestBody request: GatewayBasicRegisterRequest,
    ) = authClient.basicRegister(request.toDto())

    @GetMapping("/register-verify/{secret}")
    override fun verifyRegister(
        @PathVariable("secret") secret: String,
    ) = authClient.verifyRegister(secret)

    @PostMapping("/check/{token}")
    override fun checkAuth(
        @PathVariable("token") token: String,
    ) = authClient.checkAuth(token)
}
