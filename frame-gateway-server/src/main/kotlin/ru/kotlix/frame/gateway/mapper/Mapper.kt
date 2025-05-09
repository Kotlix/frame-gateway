package ru.kotlix.frame.gateway.mapper

import ru.kotlix.frame.auth.api.dto.BasicLoginRequest
import ru.kotlix.frame.auth.api.dto.BasicRegisterRequest
import ru.kotlix.frame.auth.api.dto.ChangeEmailRequest
import ru.kotlix.frame.auth.api.dto.ChangePasswordRequest
import ru.kotlix.frame.auth.api.dto.ChangeUsernameRequest
import ru.kotlix.frame.auth.api.dto.FullProfileInfoDto
import ru.kotlix.frame.auth.api.dto.ProfileInfoDto
import ru.kotlix.frame.gateway.api.dto.GatewayUserState
import ru.kotlix.frame.gateway.api.dto.entities.GatewayChatDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayCommunityDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayConnectionGuide
import ru.kotlix.frame.gateway.api.dto.entities.GatewayDirectoryDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayFullProfileInfoDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayInviteTokenDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayMemberDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayMessageDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayProfileInfoDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayRoleDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayVoiceDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayBasicLoginRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayBasicRegisterRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangeEmailRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangePasswordRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangeUsernameRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateChatRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateCommunityRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateDirectoryRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateRoleRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateTokenRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateVoiceRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayJoinByTokenRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewaySendMessageRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateChatRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateCommunityRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateDirectoryRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateRoleRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateVoiceRequest
import ru.kotlix.frame.parties.api.dto.entities.ChatDto
import ru.kotlix.frame.parties.api.dto.entities.CommunityDto
import ru.kotlix.frame.parties.api.dto.entities.ConnectionGuide
import ru.kotlix.frame.parties.api.dto.entities.DirectoryDto
import ru.kotlix.frame.parties.api.dto.entities.InviteTokenDto
import ru.kotlix.frame.parties.api.dto.entities.MemberDto
import ru.kotlix.frame.parties.api.dto.entities.MessageDto
import ru.kotlix.frame.parties.api.dto.entities.RoleDto
import ru.kotlix.frame.parties.api.dto.entities.VoiceDto
import ru.kotlix.frame.parties.api.dto.requests.CreateChatRequest
import ru.kotlix.frame.parties.api.dto.requests.CreateCommunityRequest
import ru.kotlix.frame.parties.api.dto.requests.CreateDirectoryRequest
import ru.kotlix.frame.parties.api.dto.requests.CreateRoleRequest
import ru.kotlix.frame.parties.api.dto.requests.CreateTokenRequest
import ru.kotlix.frame.parties.api.dto.requests.CreateVoiceRequest
import ru.kotlix.frame.parties.api.dto.requests.JoinByTokenRequest
import ru.kotlix.frame.parties.api.dto.requests.SendMessageRequest
import ru.kotlix.frame.parties.api.dto.requests.UpdateChatRequest
import ru.kotlix.frame.parties.api.dto.requests.UpdateCommunityRequest
import ru.kotlix.frame.parties.api.dto.requests.UpdateDirectoryRequest
import ru.kotlix.frame.parties.api.dto.requests.UpdateRoleRequest
import ru.kotlix.frame.parties.api.dto.requests.UpdateVoiceRequest
import ru.kotlix.frame.state.api.dto.UserState
import ru.kotlix.frame.auth.api.token.dto.UserInfo as AuthUserInfo
import ru.kotlix.frame.gateway.service.dto.UserInfo as ServiceUserInfo

fun AuthUserInfo.toServiceUserInfo() =
    ServiceUserInfo(
        id = id,
        login = login,
        username = username,
    )

fun UserState.toApi() =
    GatewayUserState(
        userId = userId,
        online = online,
        lastActive = lastActive,
    )

fun ChatDto.toApi() =
    GatewayChatDto(
        id = id,
        communityId = communityId,
        name = name,
        directoryId = directoryId,
        order = order,
    )

fun CommunityDto.toApi() =
    GatewayCommunityDto(
        id = id,
        name = name,
        description = description,
        isPublic = isPublic,
    )

fun MemberDto.toApi() =
    GatewayMemberDto(
        userId = userId,
    )

fun InviteTokenDto.toApi() =
    GatewayInviteTokenDto(
        token = token,
        expiresAt = expiresAt!!,
        isOneTime = isOneTime,
    )

fun JoinByTokenRequest.toApi() =
    GatewayJoinByTokenRequest(
        token = token,
    )

fun DirectoryDto.toApi() =
    GatewayDirectoryDto(
        id = id,
        communityId = communityId,
        name = name,
        directoryId = directoryId,
        order = order,
    )

fun MessageDto.toApi() =
    GatewayMessageDto(
        id = id,
        chatId = chatId,
        authorId = authorId,
        message = message,
        createdAt = createdAt,
    )

fun VoiceDto.toApi() =
    GatewayVoiceDto(
        id = id,
        communityId = communityId,
        name = name,
        directoryId = directoryId,
        order = order,
    )

fun ConnectionGuide.toApi() =
    GatewayConnectionGuide(
        hostAddress = hostAddress,
        channelId = channelId,
        shadowId = shadowId,
    )

fun GatewayCreateChatRequest.toDto() =
    CreateChatRequest(
        name = name,
        directoryId = directoryId,
        order = order,
    )

fun GatewayUpdateChatRequest.toDto() =
    UpdateChatRequest(
        name = name,
        directoryId = directoryId,
        order = order,
    )

fun GatewayCreateVoiceRequest.toDto() =
    CreateVoiceRequest(
        name = name,
        directoryId = directoryId,
        order = order,
    )

fun GatewayUpdateVoiceRequest.toDto() =
    UpdateVoiceRequest(
        name = name,
        directoryId = directoryId,
        order = order,
    )

fun GatewayCreateTokenRequest.toDto() =
    CreateTokenRequest(
        expiresAt = expiresAt,
        isOneTime = isOneTime,
    )

fun GatewayJoinByTokenRequest.toDto() =
    JoinByTokenRequest(
        token = token,
    )

fun GatewayUpdateCommunityRequest.toDto() =
    UpdateCommunityRequest(
        name = name,
        desc = desc,
        isPublic = isPublic,
        voiceRegion = voiceRegion,
        voiceName = voiceName,
    )

fun GatewayCreateCommunityRequest.toDto() =
    CreateCommunityRequest(
        name = name,
        desc = desc,
        isPublic = isPublic,
        voiceRegion = voiceRegion,
        voiceName = voiceName,
    )

fun GatewayUpdateDirectoryRequest.toDto() =
    UpdateDirectoryRequest(
        name = name,
        directoryId = directoryId,
        order = order,
    )

fun GatewayCreateDirectoryRequest.toDto() =
    CreateDirectoryRequest(
        name = name,
        directoryId = directoryId,
        order = order,
    )

fun GatewaySendMessageRequest.toDto() =
    SendMessageRequest(
        message = message,
    )

fun RoleDto.toApi() =
    GatewayRoleDto(
        id = id,
        communityId = communityId,
        roleName = roleName,
        priority = priority,
        rights = rights,
    )

fun GatewayCreateRoleRequest.toDto() =
    CreateRoleRequest(
        roleName = roleName,
        priority = priority,
        rights = rights,
    )

fun GatewayUpdateRoleRequest.toDto() =
    UpdateRoleRequest(
        roleName = roleName,
        priority = priority,
        rights = rights,
    )

fun GatewayBasicLoginRequest.toDto() =
    BasicLoginRequest(
        login = login,
        password = password,
    )

fun GatewayBasicRegisterRequest.toDto() =
    BasicRegisterRequest(
        login = login,
        password = password,
        username = username,
        email = email,
    )

fun GatewayChangeEmailRequest.toDto() =
    ChangeEmailRequest(
        newEmail = newEmail,
    )

fun GatewayChangeUsernameRequest.toDto() =
    ChangeUsernameRequest(
        newUsername = newUsername,
    )

fun GatewayChangePasswordRequest.toDto() =
    ChangePasswordRequest(
        newPassword = newPassword,
    )

fun FullProfileInfoDto.toApi() =
    GatewayFullProfileInfoDto(
        login = login,
        username = username,
        email = email,
    )

fun ProfileInfoDto.toApi() =
    GatewayProfileInfoDto(
        username = username,
    )
