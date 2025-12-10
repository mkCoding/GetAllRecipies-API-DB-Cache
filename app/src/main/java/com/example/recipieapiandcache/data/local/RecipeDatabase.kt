package com.example.recipieapiandcache.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RecipeEntity::class], version = 1 )
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase(){
    abstract fun recipeDatabase(): RecipeDao
    companion object{
        @Volatile private var INSTANCE: RecipeDatabase?=null

        fun getDatabase(context: Context): RecipeDatabase{
            return INSTANCE?:synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).build().also { INSTANCE = it }
            }
        }
    }

}
