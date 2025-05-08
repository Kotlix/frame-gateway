package ru.kotlix.frame.gateway.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.kotlix.frame.gateway.api.GatewayMessageApi
import ru.kotlix.frame.gateway.api.dto.entities.GatewayMessageDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewaySendMessageRequest
import ru.kotlix.frame.gateway.mapper.toApi
import ru.kotlix.frame.gateway.mapper.toDto
import ru.kotlix.frame.gateway.service.dto.UserInfo
import ru.kotlix.frame.parties.client.PartiesMessageClient

@RestController
@RequestMapping("/api/v1")
class MessageController(
    val messageApi: PartiesMessageClient,
) : GatewayMessageApi {
    @PostMapping("/chat/{chatId}/send")
    override fun sendMessage(
        @PathVariable("chatId") chatId: Long,
        @RequestBody request: GatewaySendMessageRequest,
    ): GatewayMessageDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return messageApi.sendMessage(userInfo.id, chatId, request.toDto()).toApi()
    }

    @GetMapping("/chat/{chatId}/all")
    override fun getMessages(
        @PathVariable("chatId")
        chatId: Long,
        @RequestParam("page")
        page: Long,
        @RequestParam("size")
        size: Long,
    ): List<GatewayMessageDto> {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return messageApi.getMessages(userInfo.id, chatId, page, size).map { it.toApi() }
    }

    @GetMapping("/chat-message/{id}")
    override fun getById(
        @PathVariable("id") messageId: Long,
    ): GatewayMessageDto {
        val userInfo = SecurityContextHolder.getContext().authentication.principal as UserInfo
        return messageApi.getById(userInfo.id, messageId).toApi()
    }
}
