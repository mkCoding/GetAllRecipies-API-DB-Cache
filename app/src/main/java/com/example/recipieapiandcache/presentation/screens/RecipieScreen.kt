package com.example.recipieapiandcache.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipieapiandcache.presentation.navigation.Route
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun RecipieScreen(
    recipeState: RecipeState,
    navController: NavController
){
   // grab initial data from VM via state
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("All Recipes", fontSize = 50.sp)
        when(recipeState){
            is RecipeState.Loading ->{
                CircularProgressIndicator()
            }
            is RecipeState.Success -> {
                val recipeList = recipeState.recipieData
                if(recipeList.isEmpty()){
                    Text("Recipe list empty!")
                }else{
                    LazyColumn(
                        modifier = Modifier,
                            //.fillMaxSize()
                            //.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(recipeList){item ->
                            Text(
                                text = "${item?.name}",
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp),
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.SemiBold
                            )

                            // ingredient list to string
                            val ingredientsRawList = item.ingredients
                            val ingredientsListAsString = ingredientsRawList.joinToString(",")
                            val nameOfDish = item.name
                            val imageURL = item.image

//                            val encodedName = URLEncoder.encode(nameOfDish, StandardCharsets.UTF_8.toString())
//                            val encodedIngredients = URLEncoder.encode(ingredientsListAsString, StandardCharsets.UTF_8.toString())
//                            val encodedImage = URLEncoder.encode(imageURL, StandardCharsets.UTF_8.toString())

                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(  item.image)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Recipe image",
                                contentScale = ContentScale.Crop,
                                modifier =  Modifier
                                    .clickable{
                                        //navigate to recipe details screen
                                        navController.navigate(
                                            Route.RECIPE_DETAILS_SCREEN +
                                            //nameOfDish, ingredients, imageURL
                                            "?nameOfDish=$nameOfDish"
                                            +"&ingredients=$ingredientsListAsString"
                                            +"&imageURL=$imageURL"
                                        )
                                    }
                                    .fillMaxWidth() // Changed from fillMaxSize() for LazyColumn item.padding(horizontal = 20.dp, vertical = 10.dp) // Added vertical padding for spacing
                                    .clip(RoundedCornerShape(8.dp))
                                //placeholder = rememberAsyncImagePainter()
                            )

                            HorizontalDivider(
                                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                                thickness = 4.dp
                            )

                        }
                    }
                }





            }
            is RecipeState.Error -> {
                Text("${recipeState.message}")
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun RecipieScreenPreview() {
//    RecipieScreen(
//        recipeState = RecipeState.Success(recipieData = SampleRecipes.recipeList),
//        navController = rememberNavController()
//    )
//}
