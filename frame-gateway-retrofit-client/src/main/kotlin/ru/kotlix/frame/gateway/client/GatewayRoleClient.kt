package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.*
import ru.kotlix.frame.gateway.api.dto.entities.GatewayRoleDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateRoleRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateRoleRequest

interface GatewayRoleClient {
    @GET("/api/v1/community/{communityId}/role")
    suspend fun getAllRoles(
        @Header("Authorization")
        token: String,
        @Path("communityId") communityId: Long,
    ): Response<List<GatewayRoleDto>>

    @POST("/api/v1/community/{communityId}/role")
    suspend fun createRole(
        @Header("Authorization")
        token: String,
        @Path("communityId") communityId: Long,
        @Body request: GatewayCreateRoleRequest,
    ): Response<GatewayRoleDto>

    @GET("/api/v1/role/{id}")
    suspend fun getRole(
        @Header("Authorization")
        token: String,
        @Path("id") id: Long,
    ): Response<GatewayRoleDto>

    @PUT("/api/v1/role/{id}")
    suspend fun updateRole(
        @Header("Authorization")
        token: String,
        @Path("id") id: Long,
        @Body request: GatewayUpdateRoleRequest,
    ): Response<GatewayRoleDto>

    @DELETE("/api/v1/role/{id}")
    suspend fun deleteRole(
        @Header("Authorization")
        token: String,
        @Path("id") id: Long,
    ): Response<Void>
}
