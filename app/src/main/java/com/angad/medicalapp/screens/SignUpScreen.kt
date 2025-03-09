package com.angad.medicalapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.angad.medicalapp.R
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
//            Text(text = state.value.error!!)
        }

        state.value.data != null -> {
            val data = state.value.data!!
            if (data.status == 200){
                Toast.makeText(context, "User created successfully", Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.WaitingScreenRoute(data.message))
                state.value.data = null
            } else{
                Toast.makeText(context, "User creation failed: ${data.message}", Toast.LENGTH_SHORT).show()
                state.value.data = null
            }
        }
    }

//    For taking the input from user
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE3F2FD))
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Welcome to My Medi Hub",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.timesbd)),
                modifier = Modifier.padding(vertical = 10.dp),
                color = Color(0xFF1976D2)
            )

            Text(
                text = "SignUp",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.times)),
                modifier = Modifier.padding(vertical = 10.dp),
                color = Color(0xFF1976D2)
            )


            OutlinedTextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                label = { Text(text = "Name") },
                singleLine = true
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = "Email") },
                singleLine = true
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text(text = "Password") },
                singleLine = true
            )

            OutlinedTextField(
                value = address.value,
                onValueChange = { address.value = it },
                label = { Text(text = "Address") },
                singleLine = true
            )

            OutlinedTextField(
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                label = { Text(text = "Phone number") },
                singleLine = true
            )

            OutlinedTextField(
                value = pinCode.value,
                onValueChange = { pinCode.value = it },
                label = { Text(text = "Pin code") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if ( userName.value.isNotEmpty() && email.value.isNotEmpty() &&
                        password.value.isNotEmpty() && address.value.isNotEmpty() &&
                        phoneNumber.value.isNotEmpty() && pinCode.value.isNotEmpty()
                    ) {
                        viewModel.createUser(
                            name = userName.value,
                            email = email.value,
                            password = password.value,
                            address = address.value,
                            phoneNumber = phoneNumber.value,
                            pinCode = pinCode.value
                        )
                    } else{
                        Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonColors(
                    containerColor = Color(0xFF1976D2),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF1976D2),
                    disabledContentColor = Color.White
                ),
                modifier = Modifier.width(250.dp)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.times)),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

        }
    }


}