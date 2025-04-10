package com.angad.medicalapp.api

import com.angad.medicalapp.models.AddOrderResponse
import com.angad.medicalapp.models.CreateUserResponse
import com.angad.medicalapp.models.GetAllProductsResponse
import com.angad.medicalapp.models.GetSpecificProductResponse
import com.angad.medicalapp.models.GetSpecificUserResponse
import com.angad.medicalapp.models.LoginUserResponse
import com.angad.medicalapp.models.OrderHistoryResponse
import com.angad.medicalapp.models.RecommendedProductResponse
import com.angad.medicalapp.models.SpecificOrderResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("createUser")
    suspend fun createUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("pinCode") pinCode: String

    ): Response<CreateUserResponse>


//    Function that login the user
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginUserResponse>


//    Function that fetch specific user details
    @FormUrlEncoded
    @POST("getSpecificUser")
    suspend fun getSpecificUser(
        @Field("user_id") userId: String
    ): Response<GetSpecificUserResponse>

//    Function that place order
    @GET("getAllProducts")
    suspend fun getAllProducts(): Response<GetAllProductsResponse>


//    Function that fetch specific product details
    @FormUrlEncoded
    @POST("getSpecificProduct")
    suspend fun getSpecificProduct(
        @Field("products_id") productId: String
    ): Response<GetSpecificProductResponse>


//    Function that fetch specific category product details
    @FormUrlEncoded
    @POST("getProductCategories")
    suspend fun getCategoryProducts(
        @Field("category") category: String
    ): Response<GetAllProductsResponse>


//    Function that fetch recommended product for user
    @FormUrlEncoded
    @POST("recommendedProduct")
    suspend fun getRecommendedProducts(
        @Field("user_id") userId: String
    ): Response<RecommendedProductResponse>


//    Function that add order details
    @FormUrlEncoded
    @POST("addOrderDetails")
    suspend fun addOrderDetails(
        @Field("products_id") productId:String,
        @Field("user_id") userId: String,
        @Field("product_name") productName: String,
        @Field("user_name") userName: String,
        @Field("total_price") totalPrice: Int,
        @Field("quantity") productQuantity: Int,
        @Field("message") message: String,
        @Field("address") address: String,
        @Field("phone_number") phoneNumber: String,
        @Field("product_price") productPrice: Int,
        @Field("category") category: String,
    ): Response<AddOrderResponse>


//    Function that fetch user's all order
    @FormUrlEncoded
    @POST("getUserOrder")
    suspend fun getOrderHistory(
        @Field("user_id") userId: String
    ): Response<OrderHistoryResponse>

//    Function that fetch specific order details
    @FormUrlEncoded
    @POST("getSpecificOrder")
    suspend fun getSpecificOrder(
        @Field("order_id") orderId: String
    ): Response<SpecificOrderResponse>


}