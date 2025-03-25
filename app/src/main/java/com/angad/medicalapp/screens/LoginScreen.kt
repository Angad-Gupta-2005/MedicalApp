package com.angad.medicalapp.screens

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.angad.medicalapp.R
import com.angad.medicalapp.navigation.routes.Routes
import com.angad.medicalapp.viewmodels.MyViewModel

@Composable
fun LoginScreen(
    viewModel: MyViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current

    val email = remember {
        mutableStateOf("")
    }
//    For validate the email field
    val isEmailValid = remember(email.value) {
        Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    val password = remember {
        mutableStateOf("")
    }

    val state = viewModel.loginUser.collectAsState()
    when{
        state.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        state.value.error != null -> {
            Toast.makeText(context, state.value.error, Toast.LENGTH_SHORT).show()
        }

        state.value.data != null -> {
            val data = state.value.data!!
            if (data.status == 200){

                LaunchedEffect(Unit) {
                    viewModel.getSpecificUser(userId = data.message)
                }

                val approvedState = viewModel.getSpecificUser.collectAsState()
                when{
                    approvedState.value.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }
                    }

                    approvedState.value.error != null -> {
                        Toast.makeText(context, state.value.error, Toast.LENGTH_SHORT).show()
                    }

                    approvedState.value.data != null -> {
                        val userData = approvedState.value.data!!
                        if (userData.isApproved == 1){
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            navController.navigate(Routes.BottomNavRoute){
                                popUpTo(0){
                                    inclusive = true
                                }
                            }
                            state.value.data = null
                        } else{
                            Toast.makeText(context, "You are still not approved by admin, please wait", Toast.LENGTH_SHORT).show()
                        }
                    }
                }


            } else {
                Toast.makeText(context, "Login Failed: ${data.message}", Toast.LENGTH_SHORT).show()
                state.value.data = null
            }
        }
    }

//    For input
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE3F2FD))
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Welcome to My Medi Hub",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.timesbd)),
                modifier = Modifier.padding(vertical = 10.dp),
                color = Color(0xFF1976D2)
            )

            Text(
                text = "Log In",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.times)),
                modifier = Modifier.padding(vertical = 10.dp),
                color = Color(0xFF1976D2)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = "Email") },
                modifier = Modifier.padding(8.dp),
                singleLine = true,
                isError = email.value.isNotEmpty() && !isEmailValid, // Show error if email is invalid
                supportingText = {
                    if (email.value.isNotEmpty() && !isEmailValid) {
                        Text(text = "Invalid email format", color = Color.Red)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            //Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    if ( it.length <= 8 && it.all { char -> char.isDigit() }) {
                        password.value = it
                    }
                },
                label = { Text(text = "Password") },
                modifier = Modifier.padding(8.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if (email.value.isNotEmpty() && password.value.isNotEmpty()){
                        viewModel.loginUser(
                            email = email.value,
                            password = password.value
                        )
                    }else{
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
                    text = "Login",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.times)),
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Haven't signed up? ",
                    fontFamily = FontFamily(Font(R.font.times))
                )
                Text(
                    text = "Sign Up",
                    color = Color(0xFF1976D2),
                    fontFamily = FontFamily(Font(R.font.times)),
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.SignUpScreenRoute){
                            popUpTo(Routes.LoginScreenRoute) { inclusive = true } // Clears the back stack
                            launchSingleTop = true // Prevents duplicate navigation
                        }
                    }
                )
            }
        }
    }
}