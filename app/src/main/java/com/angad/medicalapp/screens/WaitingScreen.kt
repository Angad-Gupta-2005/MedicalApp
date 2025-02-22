package com.angad.medicalapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.angad.medicalapp.navigation.routes.Routes
import com.angad.medicalapp.viewmodels.MyViewModel

@Composable
fun WaitingScreen(
    navController: NavController,
    userId: String,
    viewModel: MyViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val state = viewModel.getSpecificUser.collectAsStateWithLifecycle()
    val hasNavigated = remember { mutableStateOf(false) }//

    LaunchedEffect(userId) {
        Log.d("WaitingScreen", "LaunchedEffect triggered")
        if (state.value.data == null) {
            Log.d("WaitingScreen", "LaunchedEffect triggered1")
            viewModel.getSpecificUser(userId)
        }
    }

//    Handling the state of getSpecificUser
    when{
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
           // Text(text = state.value.error!!)
        }

        state.value.data != null && !hasNavigated.value  -> {
            val data = state.value.data!!
            Toast.makeText(context, "User fetch successfully", Toast.LENGTH_SHORT).show()
            Log.d("Approved", "WaitingScreen0: ${data.isApproved}")
            //state.value.data = null

                if (data.isApproved == 1){
                    Log.d("Approved", "WaitingScreen1: ${data.isApproved}")
                    hasNavigated.value = true//
                    navController.navigate(Routes.LoginScreenRoute)
                } else {
                    Log.d("Approved", "WaitingScreen2: ${data.isApproved}")
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        hasNavigated.value = true
                        Text(text = "Please wait, until you approved by the admin")
                    }
                }

        }
    }

}