package com.radlance.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AppService {

    @POST("auth/login")
    suspend fun login(@Body userLoginDto: UserLoginDto): LoginResponse

    @POST("auth/register")
    suspend fun register()

    suspend fun pathParam()

    @GET("auth/refresh-token")
    suspend fun refreshToken(
        @Header("Authorization") authHeader: String
    ): LoginResponse
}