package com.angad.medicalapp.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
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
    val state = viewModel.getSpecificUser.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getSpecificUser(userId)
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
        }

        state.value.data != null -> {
            val data = state.value.data!!
            Toast.makeText(context, "User fetch successfully", Toast.LENGTH_SHORT).show()
            state.value.data = null

            if (data.isApproved == 1){
                navController.navigate(Routes.HomeScreenRoute)
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Please wait, until you approved by the admin")
                }
            }

        }
    }


}