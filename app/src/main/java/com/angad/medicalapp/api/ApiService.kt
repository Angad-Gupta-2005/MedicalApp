package com.angad.medicalapp.api

import com.angad.medicalapp.models.CreateUserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {

    @POST("createUser")
    suspend fun createUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("pinCode") pinCode: String

    ): Response<CreateUserResponse>

}