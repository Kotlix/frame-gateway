package ru.kotlix.frame.gateway.service

interface AuthenticationService {
    fun authenticateByToken(token: String): Boolean
}
