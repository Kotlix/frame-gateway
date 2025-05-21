package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
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

    @PUT("/api/v1/role/{id}/unassign/{targetId}")
    suspend fun unassignRole(
        @Header("Authorization")
        token: String,
        @Path("id")
        id: Long,
        @Path("targetId")
        targetId: Long,
    ): Response<Void>

    @PUT("/api/v1/role/{id}/assign/{targetId}")
    suspend fun assignRole(
        @Header("Authorization")
        token: String,
        @Path("targetId")
        targetId: Long,
        @Path("id")
        id: Long,
    ): Response<Void>

    @GET("/api/v1/community/{communityId}/user/{targetId}/role")
    suspend fun getUserRoles(
        @Header("Authorization")
        token: String,
        @Path("communityId")
        communityId: Long,
        @Path("targetId")
        targetId: Long,
    ): Response<List<GatewayRoleDto>>
}
