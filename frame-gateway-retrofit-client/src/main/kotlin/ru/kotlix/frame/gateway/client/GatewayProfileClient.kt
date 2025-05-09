package ru.kotlix.frame.gateway.client

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import ru.kotlix.frame.gateway.api.dto.entities.GatewayFullProfileInfoDto
import ru.kotlix.frame.gateway.api.dto.entities.GatewayProfileInfoDto
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangeEmailRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangePasswordRequest
import ru.kotlix.frame.gateway.api.dto.requests.GatewayChangeUsernameRequest

interface GatewayProfileClient {
    @POST("/api/v1/profile/email")
    fun changeEmail(
        @Header("Authorization")
        token: String,
        @Body
        request: GatewayChangeEmailRequest,
    ): Response<Void>

    @GET("/api/v1/profile/email-verify/{secret}")
    fun changeEmailApply(
        @Header("Authorization")
        token: String,
        @Path("secret")
        secret: String,
    ): Response<Void>

    @POST("/api/v1/profile/username")
    fun changeUsername(
        @Header("Authorization")
        token: String,
        @Body
        request: GatewayChangeUsernameRequest,
    ): Response<Void>

    @GET("/api/v1/profile/username-verify/{secret}")
    fun changeUsernameApply(
        @Header("Authorization")
        token: String,
        @Path("secret")
        secret: String,
    ): Response<Void>

    @POST("/api/v1/profile/password")
    fun changePassword(
        @Header("Authorization")
        token: String,
        @Body
        request: GatewayChangePasswordRequest,
    ): Response<Void>

    @GET("/api/v1/profile/password-verify/{secret}")
    fun changePasswordApply(
        @Header("Authorization")
        token: String,
        @Path("secret")
        secret: String,
    ): Response<Void>

    @GET("/api/v1/profile/info")
    fun getMyProfileInfo(
        @Header("Authorization")
        token: String,
    ): Response<GatewayFullProfileInfoDto>

    @GET("/api/v1/profile/info/{userId}")
    fun getProfileInfo(
        @Header("Authorization")
        token: String,
        @Path("userId")
        userId: Long,
    ): Response<GatewayProfileInfoDto>
}
