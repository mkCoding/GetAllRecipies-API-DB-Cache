package com.example.recipieapiandcache.data.model

data class RecipeModel(
    val caloriesPerServing: Int? = 0,
    val cookTimeMinutes: Int? = 0,
    val cuisine: String? = "",
    val difficulty: String? = "",
    val id: Int? = 0,
    val image: String? = "",
    val ingredients: List<String?>? = listOf(),
    val instructions: List<String?>? = listOf(),
    val mealType: List<String?>? = listOf(),
    val name: String? = "",
    val prepTimeMinutes: Int? = 0,
    val rating: Double? = 0.0,
    val reviewCount: Int? = 0,
    val servings: Int? = 0,
    val tags: List<String?>? = listOf(),
    val userId: Int? = 0
)