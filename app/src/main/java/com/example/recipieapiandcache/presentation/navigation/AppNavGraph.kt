package com.example.recipieapiandcache.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipieapiandcache.presentation.screens.RecipeDetailsScreen
import com.example.recipieapiandcache.presentation.screens.RecipieScreen
import com.example.recipieapiandcache.presentation.screens.RecipieViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Route.RECIPE_SCREEN){

        composable(route = Route.RECIPE_SCREEN){
            val viewModel: RecipieViewModel = hiltViewModel()
            val state by viewModel.recipeState.collectAsState()
            RecipieScreen(
                recipeState = state,
                navController = navController
                )
        }

        composable(Route.RECIPE_DETAILS_SCREEN +
            "?nameOfDish={nameOfDish}&ingredients={ingredients}&imageURL={imageURL}",            arguments = listOf(
                navArgument("nameOfDish") { type = NavType.StringType },
                navArgument("ingredients") { type = NavType.StringType },
                navArgument("imageURL") { type = NavType.StringType }
            )
        ){backStackEntry ->
            val nameOfDish = backStackEntry.arguments?.getString("nameOfDish") ?: ""
            val ingredients = backStackEntry.arguments?.getString("ingredients") ?: ""
            val imageURL = backStackEntry.arguments?.getString("imageURL") ?: ""


            RecipeDetailsScreen(
                nameOfDish = nameOfDish,
                ingredients = ingredients,
                imageURL = imageURL,
                navController = navController
            )

        }

    }
}

// Add Constants below
object Route{
    const val RECIPE_SCREEN= "recipeScreen"
    const val RECIPE_DETAILS_SCREEN = "recipeDetailsScreen"
}