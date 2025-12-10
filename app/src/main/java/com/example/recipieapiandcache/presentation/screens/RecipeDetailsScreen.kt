package com.example.recipieapiandcache.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen(
    nameOfDish:String,
    ingredients:String,
    imageURL:String,
    navController: NavController
){
     // convert string ingredients to list before processing
    val ingredientsList = ingredients.split(",") // array list of ingredients


    // Add top app bar for back button on this screen


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            tint = Color.Blue,
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(90.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    //.padding(top = 30.dp)
                    .padding(16.dp),
                text = "$nameOfDish",
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image for recipie
                item {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("$imageURL")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Recipe image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                            .fillMaxWidth() // Changed from fillMaxSize() for LazyColumn item.padding(horizontal = 20.dp, vertical = 10.dp) // Added vertical padding for spacing
                            .clip(RoundedCornerShape(8.dp))
                        //placeholder = rememberAsyncImagePainter()
                    )

                }
                // List of Ingredients
                items(ingredientsList) { item ->
                    Text("$item", fontSize = 20.sp)
                }


            }
        }

    }






}



@Preview(showBackground = true)
@Composable
fun RecipeDetailsScreenPreview(){

    val recipeImageUrl: String = "https://cdn.dummyjson.com/recipe-images/5.webp" // Placeholder
    val nameOfFood = "Chicken Fried Rice"
    val ingredients ="Rice,Chicken,Olive Oil,Butter,peas,carrot"
    RecipeDetailsScreen(
        nameOfDish = nameOfFood,
        ingredients = ingredients,
        imageURL = recipeImageUrl,
        navController = rememberNavController()
    )
}