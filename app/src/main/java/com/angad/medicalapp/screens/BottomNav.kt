package com.angad.medicalapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.angad.medicalapp.models.BottomNavItem


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun BottomNav(navController: NavController) {

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

//    List of items for bottom navigation
    val bottomItem = listOf(
        BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem("Product", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart),
        BottomNavItem("Order", Icons.Filled.DateRange, Icons.Outlined.DateRange),
        BottomNavItem("Profile", Icons.Filled.Person, Icons.Outlined.Person)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                bottomItem.forEachIndexed { index, bottomNavItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(imageVector = bottomNavItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = bottomNavItem.title)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
         ContentScreen(modifier = Modifier.padding(innerPadding), navController = navController, selectedIndex)
    }

}

@Composable
fun ContentScreen(modifier: Modifier, navController: NavController, selectedIndex: Int) {
    when(selectedIndex){
//        0 -> navController.navigate(Routes.HomeScreenRoute)
//        1 -> navController.navigate(Routes.GetAllProductRoute)
//        2 -> navController.navigate(Routes.OrderHistoryRoute)
//        3 -> navController.navigate(Routes.ProfileRoute)

        0 -> HomeScreen()
        1 -> GetAllProductScreen(navController = navController)
        2 -> OrderHistoryScreen()
        3 -> ProfileScreen()
    }
}
