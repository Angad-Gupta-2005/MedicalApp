package com.angad.medicalapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.angad.medicalapp.models.GetSpecificProductResponse
import com.angad.medicalapp.viewmodels.MyViewModel

@Composable
fun AddOrderScreen(
    productId: String? = null,
    viewModel: MyViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    val addOrderState = viewModel.addOrderDetails.collectAsState()
    val getSpecificProductState = viewModel.getSpecificProduct.collectAsState()

//    *******************
    val name = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val quantity = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }

//    Calling the get specific product function
    LaunchedEffect(key1 = Unit) {
        if ( productId != null){
            viewModel.getSpecificProduct(productId)
        }
    }

//    handling the add order state
    when {
        addOrderState.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        addOrderState.value.error != null -> {
            Toast.makeText(context, addOrderState.value.error, Toast.LENGTH_SHORT).show()
        }

        addOrderState.value.data != null -> {
            val data = addOrderState.value.data!!
            Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
        }
    }


//    Handling the get specific product state
    when {
        getSpecificProductState.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        getSpecificProductState.value.error != null -> {
            Toast.makeText(context, getSpecificProductState.value.error, Toast.LENGTH_SHORT).show()
        }

        getSpecificProductState.value.data != null -> {
            Toast.makeText(context, "Product Fetch Successfully1", Toast.LENGTH_SHORT).show()
            val data = getSpecificProductState.value.data!!

            //    getSpecificProductState.value.data = null

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 10.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
//                verticalAlignment = Alignment.CenterVertically
                    ) {
                        //    For product icon
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Card(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Product Icon")
                            }
                        }

                        //    For product name
                        Column {
                            Text(text = data.name)
                            Text(text = "Category: ${data.category}")
                            Text(text = "Price: ${data.price}")
                            Text(text = "Stock: ${data.stock}")
                        }
                    }
                }

            //  Input field defined here
            //    For receiver name
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Enter Name") },
                    modifier = Modifier.padding(8.dp)
                )
            //    For receiver address
                OutlinedTextField(
                    value = address.value,
                    onValueChange = { address.value = it },
                    label = { Text("Enter Address") },
                    modifier = Modifier.padding(8.dp)
                )

            //    For receiver phone number
                OutlinedTextField(
                    value = phoneNumber.value,
                    onValueChange = { phoneNumber.value = it },
                    label = { Text("Enter Phone Number") },
                    modifier = Modifier.padding(8.dp)
                )

            //    For quantity
                OutlinedTextField(
                    value = quantity.value,
                    onValueChange = { quantity.value = it },
                    label = { Text("Enter Quantity") },
                    modifier = Modifier.padding(8.dp)
                )

            //    For message
                OutlinedTextField(
                    value = message.value,
                    onValueChange = { message.value = it },
                    label = { Text("Enter Message") },
                    modifier = Modifier.padding(8.dp)
                )

            //    Order Button
                Button(
                    onClick = {
                        if (name.value.isNotEmpty() || address.value.isNotEmpty() ||
                            phoneNumber.value.isNotEmpty() || quantity.value.isNotEmpty() ||
                            message.value.isNotEmpty()
                        ) {
                            viewModel.addOrderDetails(
                                products_id = productId!!,
                                user_id = "abc",
                                product_name = data.name,
                                user_name = name.value,
                                total_price = data.price.toInt() * quantity.value.toInt(),
                                quantity = quantity.value.toInt(),
                                message = message.value,
                                address = address.value,
                                phoneNumber = phoneNumber.value,
                                product_price = data.price.toInt(),
                                category = data.category
                            )
                        } else {
                            Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show()
                        }

                    }
                ) {
                    Text(text = "Add Order")
                }
            }
        }
    }



}

