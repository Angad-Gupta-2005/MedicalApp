package com.angad.medicalapp.repo

import com.angad.medicalapp.api.ApiBuilder
import com.angad.medicalapp.common.Results
import com.angad.medicalapp.models.AddOrderResponse
import com.angad.medicalapp.models.CreateUserResponse
import com.angad.medicalapp.models.GetAllProductsResponse
import com.angad.medicalapp.models.GetSpecificProductResponse
import com.angad.medicalapp.models.GetSpecificUserResponse
import com.angad.medicalapp.models.LoginUserResponse
import com.angad.medicalapp.models.OrderHistoryResponse
import com.angad.medicalapp.models.RecommendedProductResponse
import com.angad.medicalapp.models.SpecificOrderResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
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
            if (response.isSuccessful){
                emit(Results.Success(response))
            } else {
                emit(Results.Error(response.message()))
            }
        } catch ( e: Exception){
            emit(Results.Error(e.message.toString()))
        }
    }


//    Function that login the user
    suspend fun loginUser(
        email: String,
        password: String
    ): Flow<Results<Response<LoginUserResponse>>> = flow {
        emit(Results.Loading)
        try {
            val response = apiBuilder.api.loginUser(email, password)
            if (response.isSuccessful){
                emit(Results.Success(response))
            } else {
                emit(Results.Error(response.message()))
            }
        } catch (e: Exception){
            emit(Results.Error(e.message.toString()))
        }
    }


//    Function that fetch specific user details
    suspend fun getSpecificUser(
        userId: String
    ): Flow<Results<Response<GetSpecificUserResponse>>> = flow {
        emit(Results.Loading)
        try {
            val response = apiBuilder.api.getSpecificUser(userId = userId)
            if (response.isSuccessful){
                emit(Results.Success(response))
            } else {
                emit(Results.Error(response.message()))
            }
        } catch (e: Exception){
            emit(Results.Error(e.message.toString()))
        }
    }

//    Function that fetch all products
    suspend fun getAllProducts(): Flow<Results<Response<GetAllProductsResponse>>> = flow {
        emit(Results.Loading)
        try {
            val response = apiBuilder.api.getAllProducts()
            if (response.isSuccessful){
                emit(Results.Success(response))
            } else {
                emit(Results.Error(response.message()))
            }
        } catch (e: Exception){
            emit(Results.Error(e.message.toString()))
        }
    }

//    Function that fetch specific product category
    suspend fun getSpecificProductCategory(
        category: String
    ): Flow<Results<Response<GetAllProductsResponse>>> = flow {
        emit(Results.Loading)
        try {
            val response = apiBuilder.api.getCategoryProducts(category = category)
            if (response.isSuccessful){
                emit(Results.Success(response))
            } else {
                emit(Results.Error(response.message()))
            }
        } catch (e: Exception){
            emit(Results.Error(e.message.toString()))
        }
    }

//    Function that fetch specific product details
    suspend fun getSpecificProduct(
        productId: String
    ): Flow<Results<Response<GetSpecificProductResponse>>> = flow {
        emit(Results.Loading)
        try {
            val response = apiBuilder.api.getSpecificProduct(productId = productId)
            if (response.isSuccessful){
                emit(Results.Success(response))
            } else {
                emit(Results.Error(response.message()))
            }
        } catch (e: Exception){
            emit(Results.Error(e.message.toString()))
        }
    }


//    Function that fetch recommended product details
    suspend fun getRecommendedProduct(
        userId: String
    ): Flow<Results<Response<RecommendedProductResponse>>> = flow{
        emit(Results.Loading)
        try {
            val response = apiBuilder.api.getRecommendedProducts(userId)
            if (response.isSuccessful){
                emit(Results.Success(response))
            } else{
                emit(Results.Error(response.message()))
            }
        } catch (e: Exception){
            emit(Results.Error(e.message.toString()))
        }
    }


//    Function that place order to buy the product
    suspend fun addOrderDetails(
        products_id: String,
        user_id: String,
        product_name: String,
        user_name: String,
        total_price: Int,
        quantity: Int,
        message: String,
        address: String,
        phoneNumber: String,
        product_price: Int,
        category: String
    ): Flow<Results<Response<AddOrderResponse>>> = flow {
       emit(Results.Loading)
        try {
            val response = apiBuilder.api.addOrderDetails(
                productId = products_id,
                userId = user_id,
                productName = product_name,
                userName = user_name,
                totalPrice = total_price,
                productQuantity = quantity,
                message = message,
                address = address,
                phoneNumber = phoneNumber,
                productPrice = product_price,
                category = category
            )
            if (response.isSuccessful){
                emit(Results.Success(response))
            } else {
                emit(Results.Error(response.message()))
            }
        } catch (e: Exception){
            emit(Results.Error(e.message.toString()))
        }
    }


//    Function that fetch order history
    suspend fun getOrderHistory(userId: String): Flow<Results<Response<OrderHistoryResponse>>> = flow{
        emit(Results.Loading)
        try {
            val response = apiBuilder.api.getOrderHistory(userId)
            if (response.isSuccessful){
                emit(Results.Success(response))
            } else {
                emit(Results.Error(response.message()))
            }
        } catch (e: Exception){
            emit(Results.Error(e.message.toString()))
        }
    }

//    Function that fetch specific order
    suspend fun getSpecificOrder(orderId: String): Flow<Results<Response<SpecificOrderResponse>>> = flow{
        emit(Results.Loading)
        try {
            val response = apiBuilder.api.getSpecificOrder(orderId)
            if (response.isSuccessful){
                emit(Results.Success(response))
            } else {
                emit(Results.Error(response.message()))
            }
        } catch (e: Exception){
            emit(Results.Error(e.message.toString()))
        }

    }

}