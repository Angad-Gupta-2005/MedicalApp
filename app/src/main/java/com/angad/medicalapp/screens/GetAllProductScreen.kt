package com.angad.medicalapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.angad.medicalapp.R
import com.angad.medicalapp.models.GetAllProductsResponseItem
import com.angad.medicalapp.navigation.routes.Routes
import com.angad.medicalapp.viewmodels.MyViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetAllProductScreen(
    viewModel: MyViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    val state = viewModel.getAllProduct.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllProducts()
    }

//    Handling the state of getAllProduct
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
            Toast.makeText(context, "Product fetch successfully", Toast.LENGTH_SHORT).show()
            val product = state.value.data
            state.value.data = null

        //    For showing the list of product
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(
                            text = "All Products",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif
                        ) },
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
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp)
                    ) {
                        items(product!!){
                            ShowProductCard(res = it, navController = navController)
                        }
                    }
                }
            }


        }
    }
}


@Composable
fun ShowProductCard(res: GetAllProductsResponseItem, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable {
                navController.navigate(Routes.AddOrderRoute(productId = res.products_id))
            }
            .fillMaxSize()
            .shadow(
                elevation = 8.dp,
                spotColor = Color.Black,
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
        //    For product icon
            Column {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(80.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF90CAF9))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.products),
                        contentDescription = "Product Icon",
                        modifier = Modifier.padding(12.dp),
                        alignment = Alignment.Center
                    )
                }
            }

        //    For product name
            Column {
                Text(text = res.name)
                Text(text = "Category: ${res.category}")
                Text(text = "Price: ${res.price}")
                Text(text = "Stock: ${res.stock}")
            }
        }
    }
}