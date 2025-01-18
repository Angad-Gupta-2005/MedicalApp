package com.angad.medicalapp.repo

import com.angad.medicalapp.api.ApiBuilder
import com.angad.medicalapp.common.Results
import com.angad.medicalapp.models.CreateUserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.http.Field
import javax.inject.Inject

class Repo @Inject constructor(private val apiBuilder: ApiBuilder) {

    suspend fun  createUser(
        name: String,
        email: String,
        password: String,
        address: String,
        phoneNumber: String,
        pinCode: String
    ): Flow<Results<Response<CreateUserResponse>>> = flow{
        emit(Results.Loading)

        try {
            val response = apiBuilder.api.createUser(
                name,
                email,
                password,
                address,
                phoneNumber,
                pinCode
            )
            emit(Results.Success(response))
        } catch ( e: Exception){
            emit(Results.Error(e.message.toString()))
        }
    }
}