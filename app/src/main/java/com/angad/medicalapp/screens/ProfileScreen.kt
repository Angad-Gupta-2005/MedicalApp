package com.angad.medicalapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
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
import androidx.compose.ui.unit.dp
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
            //Toast.makeText(context, "Data fetch successfully", Toast.LENGTH_SHORT).show()
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
                        .background(Color(0xFFE3F2FD))
                        .padding(innerPadding)
                ) {
                    Box(
                        Modifier.fillMaxWidth().padding(top = 15.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile Icon",
                            modifier = Modifier.size(150.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))

//                    Text(text = "Name: ${data.name}")
//                    Text(text = "Email: ${data.email}")
//                    Text(text = "Phone: ${data.phone_number}")
//                    Text(text = "Address: ${data.address}")
//                    Text(text = "Pin code: ${data.pin_code}")
//                    Text(text = "Date of account creation: ${data.date_of_account_creation}")

                //    Profile name
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(imageVector = Icons.Outlined.Person, contentDescription = "null")
                        Column(
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Text(
                                text = "Name",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = data.name)
                        }
                    }
                //    Email name
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(imageVector = Icons.Outlined.Email, contentDescription = "null")
                        Column(
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Text(
                                text = "Email",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = data.email)
                        }
                    }

                //    Phone number
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(imageVector = Icons.Outlined.Phone, contentDescription = "null")
                        Column(
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Text(
                                text = "Phone number",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = data.phone_number)
                        }
                    }

                //    Address
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(imageVector = Icons.Outlined.Home, contentDescription = "null")
                        Column(
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Text(
                                text = "Address",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = data.address)
                        }
                    }

                //    Pin code
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(imageVector = Icons.Outlined.Info, contentDescription = "null")
                        Column(
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Text(
                                text = "Pin code",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = data.pin_code)
                        }
                    }

                //    Date of account creation
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(imageVector = Icons.Outlined.DateRange, contentDescription = "null")
                        Column(
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Text(
                                text = "Date of Account Creation",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = data.date_of_account_creation)
                        }
                    }


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