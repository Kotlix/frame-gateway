package ru.kotlix.frame.gateway.api

interface GatewayServerApi {
    fun getServers(): Map<String, List<String>>
}
