package com.angad.medicalapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angad.medicalapp.common.Results
import com.angad.medicalapp.models.CreateUserResponse
import com.angad.medicalapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    private val _createUser = MutableStateFlow(CreateUserState())
    val createUser = _createUser.asStateFlow()

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
}

//  Data class that hold the state of create user
data class CreateUserState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: CreateUserResponse? = null
)