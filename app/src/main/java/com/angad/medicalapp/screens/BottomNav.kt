package com.angad.medicalapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.angad.medicalapp.models.BottomNavItem


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun BottomNav(userId: String, navController: NavController) {

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

//    List of items for bottom navigation
    val bottomItem = listOf(
        BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem("Product", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart),
        BottomNavItem("Order", Icons.Filled.DateRange, Icons.Outlined.DateRange),

    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF1976D2),
                contentColor = Color.White,
            ){
                bottomItem.forEachIndexed { index, bottomNavItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Cyan,
                            selectedTextColor = Color.Cyan,
                            unselectedIconColor = Color(0xFFBBDEFB),
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.LightGray
                        ),
                        icon = {
                            Icon(
                                imageVector = if (selectedIndex == index) bottomNavItem.icon else bottomNavItem.unselectedIcon,
                                contentDescription = bottomNavItem.title,
                                tint = Color.White
                            )
                        },
                        label = {
                            Text(
                                text = bottomNavItem.title,
                                color = Color.White
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
         ContentScreen(modifier = Modifier.padding(innerPadding), navController = navController, selectedIndex, userId)
    }

}

@Composable
fun ContentScreen(modifier: Modifier, navController: NavController, selectedIndex: Int, userId: String) {
    when(selectedIndex){
//        0 -> navController.navigate(Routes.HomeScreenRoute)
//        1 -> navController.navigate(Routes.GetAllProductRoute)
//        2 -> navController.navigate(Routes.OrderHistoryRoute)
//        3 -> navController.navigate(Routes.ProfileRoute)

        0 -> HomeScreen(userId = userId, navController = navController)
        1 -> GetAllProductScreen(navController = navController)
        2 -> OrderHistoryScreen(userId = userId, navController = navController)

    }
}
