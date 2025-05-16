package ru.kotlix.frame.gateway.controller

import feign.FeignException
import org.springframework.http.HttpStatusCode
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.kotlix.frame.gateway.api.GatewayChatApi
import ru.kotlix.frame.gateway.api.dto.entities.GatewayChatDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateChatRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateChatRequest
import ru.kotlix.frame.gateway.mapper.toApi
import ru.kotlix.frame.gateway.mapper.toDto
import ru.kotlix.frame.gateway.service.dto.UserInfo
import ru.kotlix.frame.parties.client.PartiesChatClient

@RestController
@RequestMapping("/api/v1")
class ChatController(
    val chatApi: PartiesChatClient,
) : GatewayChatApi {
    @GetMapping("/community/{communityId}/chat")
    override fun getAllChats(
        @PathVariable("communityId")
        communityId: Long,
    ): List<GatewayChatDto> {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return chatApi.getAllChats(userInfo.id, communityId).map {
                it.toApi()
            }
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @GetMapping("/chat/{id}")
    override fun getChatById(
        @PathVariable("id")
        id: Long,
    ): GatewayChatDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return chatApi.getChatById(userInfo.id, id).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @PostMapping("/community/{communityId}/chat")
    override fun createChat(
        @PathVariable("communityId")
        communityId: Long,
        @RequestBody
        request: GatewayCreateChatRequest,
    ): GatewayChatDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return chatApi.createChat(userInfo.id, communityId, request.toDto()).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @PutMapping("/chat/{id}")
    override fun updateChat(
        @PathVariable("id")
        id: Long,
        @RequestBody
        request: GatewayUpdateChatRequest,
    ): GatewayChatDto {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return chatApi.updateChat(userInfo.id, id, request.toDto()).toApi()
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }

    @DeleteMapping("/chat/{id}")
    override fun deleteChat(
        @PathVariable("id") id: Long,
    ) {
        try {
            val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
            return chatApi.deleteChat(userInfo.id, id)
        } catch (e: FeignException) {
            throw ResponseStatusException(HttpStatusCode.valueOf(e.status()))
        }
    }
}
