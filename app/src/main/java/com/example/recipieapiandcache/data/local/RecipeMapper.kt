package com.example.recipieapiandcache.data.local

import com.example.recipieapiandcache.data.model.RecipeModel
import com.example.recipieapiandcache.data.model.RecipiesModel

//object RecipeMapper {
fun RecipeModel.toEntity(): RecipeEntity {
    return RecipeEntity(
        id = id ?: 0,
        name = name.orEmpty(),
        ingredients = ingredients?.map { it.orEmpty() } ?: emptyList(),
        instructions = instructions?.map { it.orEmpty() } ?: emptyList(),
        prepTimeMinutes = prepTimeMinutes ?: 0,
        cookTimeMinutes = cookTimeMinutes ?: 0,
        servings = servings ?: 0,
        difficulty = difficulty.orEmpty(),
        cuisine = cuisine.orEmpty(),
        caloriesPerServing = caloriesPerServing ?: 0,
        tags = tags?.map { it.orEmpty() } ?: emptyList(),
        userId = userId ?: 0,
        image = image.orEmpty(),
        rating = rating ?: 0.0,
        reviewCount = reviewCount ?: 0,
        mealType = mealType?.map { it.orEmpty() } ?: emptyList()
    )
}

//}
