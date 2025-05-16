package ru.kotlix.frame.gateway.controller

import feign.FeignException
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.kotlix.frame.gateway.api.GatewayUserStateApi
import ru.kotlix.frame.gateway.api.dto.GatewayUserState
import ru.kotlix.frame.gateway.mapper.toApi
import ru.kotlix.frame.state.client.UserStateClient

@RestController
@RequestMapping("/api/v1")
class UserStateController(
    private val stateClient: UserStateClient,
) : GatewayUserStateApi {
    @GetMapping("/status/{userId}")
    override fun getUserStatus(
        @PathVariable
        userId: Long,
    ): GatewayUserState =
        try {
            stateClient.getUserStatus(userId).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
}
