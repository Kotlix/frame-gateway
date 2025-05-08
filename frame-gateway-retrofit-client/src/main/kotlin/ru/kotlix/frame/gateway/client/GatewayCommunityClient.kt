package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kotlix.frame.gateway.api.dto.entities.GatewayCommunityDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayInviteTokenDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayMemberDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateCommunityRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayCreateTokenRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayJoinByTokenRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayUpdateCommunityRequest

interface GatewayCommunityClient {
    @GET("/api/v1/community/{communityId}")
    suspend fun getById(
        @Path("communityId")
        communityId: Long,
    ): Response<GatewayCommunityDto>

    @POST("/api/v1/community")
    suspend fun create(
        @Body
        dto: GatewayCreateCommunityRequest,
    ): Response<GatewayCommunityDto>

    @PUT("/api/v1/community/{communityId}")
    suspend fun update(
        @Path("communityId")
        communityId: Long,
        @Body
        request: GatewayUpdateCommunityRequest,
    ): Response<GatewayCommunityDto>

    @DELETE("/api/v1/community/{communityId}")
    suspend fun delete(
        @Path("communityId")
        communityId: Long,
    ): Response<Void>

    @GET("/api/v1/all-communities")
    suspend fun findAllPublicWithFilter(
        @Query("q")
        name: String?,
        @Query("page")
        page: Long,
        @Query("size")
        size: Long,
    ): Response<List<GatewayCommunityDto>>

    @GET("/api/v1/my-communities")
    suspend fun findAllMine(): Response<List<GatewayCommunityDto>>

    @GET("/api/v1/community/{communityId}/members")
    suspend fun getMembers(
        @Path("communityId")
        communityId: Long,
    ): Response<List<GatewayMemberDto>>

    @POST("/api/v1/community/{communityId}/join")
    suspend fun joinCommunity(
        @Path("communityId")
        communityId: Long,
    ): Response<Void>

    @POST("/api/v1/community/{communityId}/leave")
    suspend fun leaveCommunity(
        @Path("communityId")
        communityId: Long,
    ): Response<Void>

    @POST("/api/v1/community/{communityId}/token")
    suspend fun createInviteToken(
        @Path("communityId")
        communityId: Long,
        @Body
        request: GatewayCreateTokenRequest,
    ): Response<GatewayInviteTokenDto>

    @POST("/api/v1/community-join-token")
    suspend fun joinByInviteToken(
        @Body
        request: GatewayJoinByTokenRequest,
    ): Response<Void>
}