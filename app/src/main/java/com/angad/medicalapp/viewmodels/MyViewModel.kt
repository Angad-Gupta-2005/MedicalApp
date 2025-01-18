package com.angad.medicalapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angad.medicalapp.models.CreateUserResponse
import com.angad.medicalapp.repo.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val _createUser = MutableStateFlow<CreateUserResponse?>(null)
    val createUser = _createUser.asStateFlow()

//    Create an object of repo
    val repo = Repo()

    //    Function that create the user
    fun createUser(
        name: String,
        email: String,
        password: String,
        address: String,
        phoneNumber: String,
        pinCode: String

    ) {
        viewModelScope.launch {
            repo.createUser(name, email, password, phoneNumber, pinCode, address)
        }
    }
}