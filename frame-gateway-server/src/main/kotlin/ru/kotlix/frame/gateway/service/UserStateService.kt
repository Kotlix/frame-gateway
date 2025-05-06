package ru.kotlix.frame.gateway.service

import ru.kotlix.frame.gateway.service.dto.UserInfo

interface UserStateService {
    fun getUserStatus(userId: Long): UserInfo

    fun updateUserStatus(
        userId: Long,
        online: Boolean,
    )
}
