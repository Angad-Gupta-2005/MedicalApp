package com.angad.medicalapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angad.medicalapp.common.Results
import com.angad.medicalapp.models.AddOrderResponse
import com.angad.medicalapp.models.CreateUserResponse
import com.angad.medicalapp.models.GetAllProductsResponse
import com.angad.medicalapp.models.GetAllProductsResponseItem
import com.angad.medicalapp.models.GetSpecificProductResponse
import com.angad.medicalapp.models.GetSpecificUserResponse
import com.angad.medicalapp.models.LoginUserResponse
import com.angad.medicalapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

//    Mutable state flow for create user
    private val _createUser = MutableStateFlow(CreateUserState())
    val createUser = _createUser.asStateFlow()

//    Mutable state for login user
    private val _loginUser = MutableStateFlow(LoginUserState())
    val loginUser = _loginUser.asStateFlow()

//    Mutable state for getSpecificUser
    private val _getSpecificUser = MutableStateFlow(GetSpecificUserState())
    val getSpecificUser = _getSpecificUser.asStateFlow()

//    Mutable state for getAllProducts
    private val _getAllProducts = MutableStateFlow(GetAllProductState())
    val getAllProduct = _getAllProducts.asStateFlow()

//    Mutable state for addOrderDetails
    private val _addOrderDetails = MutableStateFlow(AddOrderDetailsState())
    val addOrderDetails = _addOrderDetails.asStateFlow()

//    Mutable state for getSpecificProduct
    private val _getSpecificProduct = MutableStateFlow(GetSpecificProductState())
    val getSpecificProduct = _getSpecificProduct.asStateFlow()


    //    Function that create the user
    fun createUser(
        name: String,
        email: String,
        password: String,
        address: String,
        phoneNumber: String,
        pinCode: String

    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.createUser(
                name, email, password, address, phoneNumber, pinCode
            ).collect{
                when(it){
                    is Results.Loading -> {
                        _createUser.value = CreateUserState(isLoading = true)
                    }
                    is Results.Error -> {
                        _createUser.value = CreateUserState(error = it.message, isLoading = false)
                    }
                    is Results.Success -> {
                        _createUser.value = CreateUserState(data = it.data.body(), isLoading = false)
                    }
                }
            }
        }
    }

//    Function that login the user
    fun loginUser(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.loginUser(email = email, password = password).collect{
                when(it){
                    is Results.Loading -> {
                        _loginUser.value = LoginUserState(isLoading = true)
                    }

                    is Results.Error -> {
                        _loginUser.value = LoginUserState(error = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _loginUser.value = LoginUserState(data = it.data.body(), isLoading = false)
                    }
                }
            }
        }
    }


//    Function that fetch specific user details
    fun getSpecificUser(userId: String){
        viewModelScope.launch(Dispatchers.IO) {
                repo.getSpecificUser(userId = userId).collect{
                    when(it){
                        is Results.Loading -> {
                            _getSpecificUser.value = GetSpecificUserState(isLoading = true)
                        }

                        is Results.Error -> {
                            _getSpecificUser.value = GetSpecificUserState(error = it.message, isLoading = false)
                        }

                        is Results.Success -> {
                            _getSpecificUser.value = GetSpecificUserState(data = it.data.body(), isLoading = false)
                        }
                    }
                }
            }
        }


//    Function that fetch all product
    fun getAllProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllProducts().collect{
                when(it){
                    is Results.Loading -> {
                        _getAllProducts.value = GetAllProductState(isLoading = true)
                    }

                    is Results.Error -> {
                        _getAllProducts.value = GetAllProductState(error = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _getAllProducts.value = GetAllProductState(data = it.data.body(), isLoading = false)
                    }
                }
            }
        }
    }


//    Function that fetch specific product details
    fun getSpecificProduct(productId: String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSpecificProduct(productId = productId).collect{
                when(it){
                    is Results.Loading -> {
                        _getSpecificProduct.value = GetSpecificProductState(isLoading = true)
                    }

                    is Results.Error -> {
                        _getSpecificProduct.value = GetSpecificProductState(error = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _getSpecificProduct.value = GetSpecificProductState(data = it.data.body(), isLoading = false)
                    }
                }
            }
        }
    }


//    Function that place order
    fun addOrderDetails(
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
    ){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addOrderDetails(
                products_id = products_id,
                user_id = user_id,
                product_name = product_name,
                user_name = user_name,
                total_price = total_price,
                quantity = quantity,
                message = message,
                address = address,
                phoneNumber = phoneNumber,
                product_price = product_price,
                category = category
            ).collect{
                when(it){
                    is Results.Loading -> {
                        _addOrderDetails.value = AddOrderDetailsState(isLoading = true)
                    }

                    is Results.Error -> {
                        _addOrderDetails.value = AddOrderDetailsState(error = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _addOrderDetails.value = AddOrderDetailsState(data = it.data.body(), isLoading = false)
                    }
                }
            }
        }
    }


}

//  Data class that hold the state of create user
data class CreateUserState(
    val isLoading: Boolean = false,
    val error: String? = null,
    var data: CreateUserResponse? = null
)

//    Login user response state
data class LoginUserState(
    val isLoading: Boolean = false,
    val error: String? = null,
    var data: LoginUserResponse? = null
)

//    Get specific user state
data class GetSpecificUserState(
    val isLoading: Boolean = false,
    val error: String? = null,
    var data: GetSpecificUserResponse? = null
)

//    Get all products state
data class GetAllProductState(
    val isLoading: Boolean = false,
    val error: String? = null,
    var data: GetAllProductsResponse? = null
)


//    Get specific product state
data class GetSpecificProductState(
    val isLoading: Boolean = false,
    val error: String? = null,
    var data: GetSpecificProductResponse? = null
)


//    Add order details state
data class AddOrderDetailsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: AddOrderResponse? = null
)