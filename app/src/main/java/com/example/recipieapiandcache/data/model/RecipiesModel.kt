package com.example.recipieapiandcache.data.model

data class RecipiesModel(
    val limit: Int? = 0,
    val recipes: List<RecipeModel?>? = listOf(),
    val skip: Int? = 0,
    val total: Int? = 0
)