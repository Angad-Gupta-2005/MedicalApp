package com.angad.medicalapp.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.angad.medicalapp.viewmodels.MyViewModel

@Composable
fun CategoryScreen(
    category: String,
    viewModel: MyViewModel = hiltViewModel(),
) {
    Text(text = "Category Screen")
}