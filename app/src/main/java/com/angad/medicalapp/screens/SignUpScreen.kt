package com.angad.medicalapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.angad.medicalapp.navigation.routes.Routes
import com.angad.medicalapp.viewmodels.MyViewModel

@Composable
fun SignUpScreen(viewModel: MyViewModel = hiltViewModel(), navController: NavController) {

    val context = LocalContext.current

//    Creating mutableState for input field to create user
    val userName = remember {
        mutableStateOf("")
    }

    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val address = remember {
        mutableStateOf("")
    }

    val phoneNumber = remember {
        mutableStateOf("")
    }

    val pinCode = remember {
        mutableStateOf("")
    }

//    Managing the state in order to create user i.e., for signUp
    val state = viewModel.createUser.collectAsState()
    when {
        state.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.value.error != null -> {
            Toast.makeText(context, state.value.error, Toast.LENGTH_SHORT).show()
        }

        state.value.data != null -> {
            Toast.makeText(context, "SignUp Successful", Toast.LENGTH_SHORT).show()
        }
    }

//    For taking the input from user
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "SignUp Screen")


        OutlinedTextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            label = { Text(text = "Name") },
            singleLine = true
        )
//        For space
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(text = "Email") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "Password") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = address.value,
            onValueChange = { address.value = it },
            label = { Text(text = "Address") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            label = { Text(text = "Phone number") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = pinCode.value,
            onValueChange = { pinCode.value = it },
            label = { Text(text = "Pin code") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if ( userName.value.isNotEmpty() || email.value.isNotEmpty() ||
                    password.value.isNotEmpty() || address.value.isNotEmpty() ||
                    phoneNumber.value.isNotEmpty() || pinCode.value.isNotEmpty()
                ) {
                    viewModel.createUser(
                        name = userName.value,
                        email = email.value,
                        password = password.value,
                        address = address.value,
                        phoneNumber = phoneNumber.value,
                        pinCode = pinCode.value
                    )
                //    Goto the waiting screen
                    navController.navigate(Routes.WaitingScreenRoute)

                } else{
                    Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = "Sign Up")
        }

    }

}