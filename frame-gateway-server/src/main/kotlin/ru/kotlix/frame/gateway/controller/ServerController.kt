package ru.kotlix.frame.gateway.controller

import feign.FeignException
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.kotlix.frame.gateway.api.GatewayServerApi
import ru.kotlix.frame.voice.client.VoiceClient

@RestController
@RequestMapping("/api/v1")
class ServerController(
    val voiceClient: VoiceClient,
) : GatewayServerApi {
    @GetMapping("/voice/servers")
    override fun getServers(): Map<String, List<String>> {
        try {
            return voiceClient.getServers()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }
}
