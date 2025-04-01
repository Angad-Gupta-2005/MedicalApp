package com.angad.medicalapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.angad.medicalapp.R
import com.angad.medicalapp.models.CategoryData
import com.angad.medicalapp.models.getCategoryList
import com.angad.medicalapp.navigation.routes.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val scrollState = rememberScrollState()
    val categoryList = getCategoryList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Dear Customer!",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 30.sp
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { /* Handle notification click */ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(Routes.ProfileRoute)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFE3F2FD))
                .verticalScroll(scrollState)
        ) {

        //    Banner row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.medical_banner),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillWidth
                )
            }

        //    For category
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 15.dp, bottom = 10.dp)
            ) {
//                Icon(imageVector = Icons.Default.Menu, contentDescription = "Category")
                Icon(
                    painter = painterResource(id = R.drawable.categories),
                    contentDescription = "Category",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Category",
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        //    For category list
            LazyRow {
                items(categoryList) { category ->
                    CategoryItem(category = category, navController = navController)
                }
            }

        //    Recommended product row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 15.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Icon(imageVector = Icons.Default.Info, contentDescription = "Recommended")
                Icon(
                    painter = painterResource(id = R.drawable.like),
                    contentDescription = "Recommended",
                    modifier = Modifier.size(30.dp)
                )

                Text(
                    text = "Recommended for you",
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        //    Recommend product list
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            ) {
                //    First category
                Column(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(horizontal = 6.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF90CAF9))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.products),
                            contentDescription = "Paracetamol",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.padding(25.dp)
                        )
                    }
                    Text(
                        text = "Paracetamol",
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                //    Second category
                Column(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(horizontal = 6.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF90CAF9))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.products),
                            contentDescription = "Amoxicillin",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.padding(25.dp)
                        )
                    }
                    Text(
                        text = "Amoxicillin",
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                //    Third category
                Column(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(horizontal = 6.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF90CAF9))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.products),
                            contentDescription = "Omeprazole",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.padding(25.dp)
                        )
                    }
                    Text(
                        text = "Omeprazole",
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

            }
        //

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            ) {
                //    First category
                Column(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(horizontal = 6.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF64B5F6))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.products),
                            contentDescription = "Paracetamol",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.padding(25.dp)
                        )
                    }
                    Text(
                        text = "Paracetamol",
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                //    Second category
                Column(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(horizontal = 6.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF64B5F6))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.products),
                            contentDescription = "Amoxicillin",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.padding(25.dp)
                        )
                    }
                    Text(
                        text = "Amoxicillin",
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                //    Third category
                Column(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(horizontal = 6.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF64B5F6))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.products),
                            contentDescription = "Omeprazole",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.padding(25.dp)
                        )
                    }
                    Text(
                        text = "Omeprazole",
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

            }

        //    For space in bottom
            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}

//    For category UI
@Composable
fun CategoryItem(category: CategoryData, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .width(95.dp)
    ) {
        Card(
            modifier = Modifier.clickable {
                navController.navigate(Routes.CategoryScreenRoute(category = category.name))
            },
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE))
        ) {
            Image(
                painter = painterResource(category.imageRes),
                contentDescription = category.name,
                alignment = Alignment.Center,
                contentScale = ContentScale.Inside,
                modifier = Modifier.padding(20.dp)
            )
        }
        Text(
            text = category.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

}
