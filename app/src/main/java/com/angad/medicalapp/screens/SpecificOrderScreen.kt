package com.angad.medicalapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.angad.medicalapp.viewmodels.MyViewModel

@Composable
fun SpecificOrderScreen(orderId: String, viewModel: MyViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state = viewModel.getSpecificOrder.collectAsState()

    LaunchedEffect(orderId) {
        viewModel.getSpecificOrder(orderId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
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
                val order = state.value.data!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Order Details",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    DetailRow(label = "Product Name:", value = order.product_name)
                    DetailRow(label = "Quantity:", value = order.quantity.toString())
                    DetailRow(label = "Price:", value = "₹${order.product_price}")
                    DetailRow(label = "Category:", value = order.category)
                    DetailRow(label = "Order Date:", value = order.date_of_order_creation)
                    DetailRow(label = "Total Price:", value = "₹${order.total_price}")
                    DetailRow(label = "Address:", value = order.address)
                    DetailRow(label = "Phone Number:", value = order.phone_number)
                    DetailRow(label = "Message:", value = order.message)
                    DetailRow(label = "User Name:", value = order.user_name)
                    DetailRow(label = "Status:", value = order.isApproved.toString())
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = value)
    }
}
