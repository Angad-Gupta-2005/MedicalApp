package com.angad.medicalapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.angad.medicalapp.navigation.routes.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Dear Customer!",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 30.sp
                ) },
                actions = {
                    IconButton(onClick = { /* Handle notification click */ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.Black,
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
                            tint = Color.Black,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
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
                    Card {
                        Image(
                            painter = painterResource(id = R.drawable.weight),
                            contentDescription = "Fitness",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                    Text(
                        text = "Fitness",
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
                    Card {
                        Image(
                            painter = painterResource(id = R.drawable.personal_care),
                            contentDescription = "Personal care",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                    Text(
                        text = "Personal care",
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
                    Card {
                        Image(
                            painter = painterResource(id = R.drawable.medicine),
                            contentDescription = "Familymedicine",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                    Text(
                        text = "Familycare",
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

            //    Fourth category
                Column(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(horizontal = 6.dp)
                ) {
                    Card {
                        Image(
                            painter = painterResource(id = R.drawable.lifestyles),
                            contentDescription = "Lifestyle",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                    Text(
                        text = "Lifestyle",
                        fontSize = 12.sp,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
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
                    Card {
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
                    Card {
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
                    Card {
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
                    Card {
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
                    Card {
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
                    Card {
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

        }
    }
}