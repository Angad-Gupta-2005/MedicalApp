package com.angad.medicalapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
fun CategoryScreen(
    category: String,
    viewModel: MyViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    val state = viewModel.getAllProduct.collectAsState()

    LaunchedEffect(category) {
        if (state.value.data == null) {
            Log.d("TAG", "CategoryScreen: abc")
            viewModel.getCategoryProducts(category)
        }
    }


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

            //state.value.data = null

            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(
                            text = "$category Products",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif
                        )
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
                    if (data.isNotEmpty()){
                        LazyColumn {
                            items(data){
                                ShowProduct(res = it, navController = navController)
                            }
                        }
                    } else{
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Product Not Available",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun ShowProduct(res: GetAllProductsResponseItem, navController: NavController) {
    Log.d("TAG", "ShowProduct: ${res.name}")
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(Routes.AddOrderRoute(productId = res.products_id))
            }
            .fillMaxSize()
            .shadow(
                elevation = 8.dp,
                spotColor = Color.Black,
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF) // White Card Color
        ),
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
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
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
                Log.d("TAG", "ShowProduct: ${res.name}")
                Text(text = res.name)
                Text(text = "Category: ${res.category}")
                Text(text = "Price: ${res.price}")
                Text(text = "Stock: ${res.stock}")
            }
        }
    }
}
