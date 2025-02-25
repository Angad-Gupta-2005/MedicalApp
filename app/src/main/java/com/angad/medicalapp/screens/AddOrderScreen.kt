package com.angad.medicalapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.angad.medicalapp.models.GetSpecificProductResponse
import com.angad.medicalapp.viewmodels.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddOrderScreen(
    productId: String? = null,
    viewModel: MyViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    val addOrderState = viewModel.addOrderDetails.collectAsState()
    val getSpecificProductState = viewModel.getSpecificProduct.collectAsState()


//    Calling the get specific product function
    LaunchedEffect(key1 = Unit) {
        if ( productId != null){
            Log.d("TAG", "AddOrderScreen: GetSpecific Product")
            viewModel.getSpecificProduct(productId)
        }
    }

//    For getting the userId from the preferences
    val userId = viewModel.userIdByPref.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.getUserIdByPref()
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
            val data = addOrderState.value.data
           // Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
            LaunchedEffect(key1 = Unit) {
                if (data != null){
                    Log.d("TAG", "AddOrderScreen: Order Successfully")
                    Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
                }
            }
            addOrderState.value.data = null
            Log.d("TAG", "AddOrderScreen: ${data?.message}")
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
//            Log.d("TAG", "AddOrderScreen: Fetch Successfully")
//            Toast.makeText(context, "Product Fetch Successfully1", Toast.LENGTH_SHORT).show()
            val data = getSpecificProductState.value.data

            LaunchedEffect(key1 = Unit) {
                if (data != null) {
                    Log.d("TAG", "AddOrderScreen: Fetch Successfully")
                    Toast.makeText(context, "Product Fetch Successfully1", Toast.LENGTH_SHORT).show()
                }
            }


//            Column(
//                modifier = Modifier.fillMaxWidth(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                PlaceOrder(data, viewModel, productId, userId.value)
//            }
//            getSpecificProductState.value.data = null

            data?.let {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PlaceOrder(it, viewModel, productId, userId.value, navController)
                }
            }
        }
    }



}

@Composable
fun PlaceOrder(data: GetSpecificProductResponse, viewModel: MyViewModel, productId: String?, userId: String?, navController: NavController) {

    val name = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val quantity = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }

    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(top = 50.dp, start = 8.dp, end = 8.dp, bottom = 10.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
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
        modifier = Modifier.padding(4.dp),
        singleLine = true
    )
    //    For receiver address
    OutlinedTextField(
        value = address.value,
        onValueChange = { address.value = it },
        label = { Text("Enter Address") },
        modifier = Modifier.padding(4.dp),
        singleLine = true
    )

    //    For receiver phone number
    OutlinedTextField(
        value = phoneNumber.value,
        onValueChange = { phoneNumber.value = it },
        label = { Text("Enter Phone Number") },
        modifier = Modifier.padding(4.dp),
        singleLine = true
    )

    //    For quantity
    OutlinedTextField(
        value = quantity.value,
        onValueChange = { quantity.value = it },
        label = { Text("Enter Quantity") },
        modifier = Modifier.padding(4.dp),
        singleLine = true
    )

    //    For message
    OutlinedTextField(
        value = message.value,
        onValueChange = { message.value = it },
        label = { Text("Enter Message") },
        modifier = Modifier.padding(4.dp),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(20.dp))
    //    Order Button
    Button(
        onClick = {
            if (name.value.isNotEmpty() && address.value.isNotEmpty() &&
                phoneNumber.value.isNotEmpty() && quantity.value.isNotEmpty() &&
                message.value.isNotEmpty()
            ) {
                viewModel.addOrderDetails(
                    products_id = productId!!,
                    user_id = userId!!,
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
                name.value = ""
                address.value = ""
                phoneNumber.value = ""
                quantity.value = ""
                message.value = ""
              //  navController.navigateUp()
            } else {
                Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show()
            }

        }
    ) {
        Text(text = "Add Order")
    }
}
