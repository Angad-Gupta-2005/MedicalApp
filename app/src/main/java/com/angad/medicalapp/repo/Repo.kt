package com.angad.medicalapp.repo

import com.angad.medicalapp.api.ApiBuilder
import com.angad.medicalapp.models.CreateUserResponse
import retrofit2.Response
import retrofit2.http.Field

class Repo {

    suspend fun  createUser(
        name: String,
        email: String,
        password: String,
        address: String,
        phoneNumber: String,
        pinCode: String
    ): Response<CreateUserResponse>{
        return ApiBuilder.api.createUser(
            name,
            email,
            password,
            address,
            phoneNumber,
            pinCode

        )
    }
}