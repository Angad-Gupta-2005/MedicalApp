package com.angad.medicalapp.models

import com.angad.medicalapp.R

data class CategoryData(
    val name: String,
    val imageRes: Int
)

fun getCategoryList(): List<CategoryData> {
    return listOf(
        CategoryData("Fitness", R.drawable.weight),
        CategoryData("Personal care", R.drawable.personal_care),
        CategoryData("Familycare", R.drawable.medicine),
        CategoryData("Lifestyle", R.drawable.lifestyles)
    )
}