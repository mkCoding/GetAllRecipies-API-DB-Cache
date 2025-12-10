package com.example.recipieapiandcache.domain.repository

import com.example.recipieapiandcache.data.api.RecipieApi
import com.example.recipieapiandcache.data.local.RecipeEntity
import com.example.recipieapiandcache.data.local.RecipeDao
import com.example.recipieapiandcache.data.local.toEntity
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipeApi: RecipieApi,
    private val recipeDao: RecipeDao
){

    // Load from DB first
    suspend fun getRecipes():List<RecipeEntity> {
        // 1. Load from DB first
        val cache = recipeDao.getAllRecipes()

        // 2. If DB is not empty use it immediatley
        if (cache.isNotEmpty()) {
            return cache
        }

        //3. DB is empty try fetching from network
        return try {
            val apiResponse = recipeApi.getAllRecipies()

            val recipeEntities = apiResponse.recipes
                ?.filterNotNull()
                ?.map { it.toEntity() }
                ?: emptyList()

            // Save fresh data to DB
            recipeDao.insertRecipe(recipeEntities)

            recipeEntities  // return fresh internet data

        } catch (e: Exception) {
            // 4. Internet failed AND DB is empty → return empty list
            emptyList()
        }
    }
}


