package com.example.recipieapiandcache.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {
    // create insert and select methods that our app can call
    // to reference DB actions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: List<RecipeEntity>)

    @Query("Select * From recipes")
    suspend fun getAllRecipes(): List<RecipeEntity>
}