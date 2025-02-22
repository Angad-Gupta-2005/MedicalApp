package com.angad.medicalapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun LoginScreen(
    viewModel: MyViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current

    val email = remember {
        mutableStateOf("")
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
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.HomeScreenRoute)
                state.value.data = null
            } else {
                Toast.makeText(context, "Login Failed: ${data.message}", Toast.LENGTH_SHORT).show()
                state.value.data = null
            }
        }
    }

//    For input 
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Text(text = "Login Screen")
        
        Spacer(modifier = Modifier.height(30.dp))
        
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(text = "Email") },
            modifier = Modifier.padding(8.dp),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password.value, 
            onValueChange = { password.value = it },
            label = { Text(text = "Password") },
            modifier = Modifier.padding(8.dp),
            singleLine = true
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
            }
        ) {
            Text(text = "Login")
        }
        
    }
}