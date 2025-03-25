package com.angad.medicalapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.angad.medicalapp.navigation.routes.Routes
import com.angad.medicalapp.viewmodels.MyViewModel

@Composable
fun WaitingScreen(
    userId: String,
    navController: NavController,
    viewModel: MyViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val state = viewModel.getSpecificUser.collectAsState()
    val hasNavigated = remember { mutableStateOf(false) } // Prevent multiple navigation

    LaunchedEffect(userId) {
        viewModel.getSpecificUser(userId)
    }

//    Handling the state of getSpecificUser
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
            val data = state.value.data!!
            Log.d("Approved", "WaitingScreen0: ${data.isApproved}")
            //Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

            if (data.isApproved == 1 && !hasNavigated.value) {
                hasNavigated.value = true // Set flag to true to prevent multiple navigation
                Log.d("Approved", "WaitingScreen1: ${data.isApproved}")
                navController.navigate(Routes.LoginScreenRoute) {
                    state.value.data = null
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            } else if (data.isApproved == 0) {
                Log.d("Approved", "WaitingScreen2: ${data.isApproved}")
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFE3F2FD)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Please wait, until you approved by the admin",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

        }
    }

}