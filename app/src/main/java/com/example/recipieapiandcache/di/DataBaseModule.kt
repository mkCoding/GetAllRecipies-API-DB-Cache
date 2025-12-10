package com.example.recipieapiandcache.di

import android.content.Context
import androidx.room.Room
import com.example.recipieapiandcache.data.local.RecipeDao
import com.example.recipieapiandcache.data.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext context: Context): RecipeDatabase {
        return Room.databaseBuilder(
            context,
            RecipeDatabase::class.java,
            "recipe.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao{
        return recipeDatabase.recipeDatabase()
    }



}