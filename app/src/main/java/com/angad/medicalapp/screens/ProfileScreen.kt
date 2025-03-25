package com.angad.medicalapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.angad.medicalapp.navigation.routes.Routes
import com.angad.medicalapp.viewmodels.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userId: String,
    navController: NavController,
    viewModel: MyViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val state = viewModel.getSpecificUser.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getSpecificUser(userId)
    }

//    Handling the state
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
            Toast.makeText(context, "Data fetch successfully", Toast.LENGTH_SHORT).show()
            val data = state.value.data!!

            var showLogoutDialog by remember { mutableStateOf(false) }
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Profile",
                                fontSize = 24.sp,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.navigateUp()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { showLogoutDialog = true }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "Settings",
                                    tint = Color.White
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF1976D2),
                            titleContentColor = Color.White
                        )
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(text = "Name: ${data.name}")
                    Text(text = "Email: ${data.email}")
                    Text(text = "Phone: ${data.phone_number}")
                    Text(text = "Address: ${data.address}")
                    Text(text = "Pin code: ${data.pin_code}")
                    Text(text = "Date of account creation: ${data.date_of_account_creation}")
                }
            }
            // Logout Confirmation Dialog
            if (showLogoutDialog) {
                AlertDialog(
                    onDismissRequest = { showLogoutDialog = false },
                    title = { Text("Logout") },
                    text = { Text("Are you sure you want to logout?") },
                    confirmButton = {
                        TextButton(onClick = {
                            showLogoutDialog = false
                            // Add logout logic here (e.g., navigate to login screen)
                            viewModel.logoutUser()
                            navController.navigate(Routes.LoginScreenRoute)
                        }) {
                            Text("Logout")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showLogoutDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )

            }
        }
    }


}