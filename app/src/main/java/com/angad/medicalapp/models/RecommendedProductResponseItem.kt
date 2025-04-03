package com.angad.medicalapp.models

data class RecommendedProductResponseItem(
    val category: String,
    val id: Int,
    val name: String,
    val price: Double,
    val products_id: String,
    val stock: Int
)