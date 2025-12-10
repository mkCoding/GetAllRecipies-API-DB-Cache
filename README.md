# Description
- Create an application Fetch recipes from an API call and caches them to Room DB locally
- Also allow user to view Recipes and ingredients through the app

Combine cached + network data

# Users should be able to:
- Open the app click on a dish
- Details screen should display dish ingredients an dimage
- Data should be able to view the data offline

### Additional Notes:
- Store recipe title + cuisine + image in Room
- MVVM + StateFlow
- Screen 1: List of recipes
- Screen 2: Detail screen (ingredients list)

# What I learned
- How to add data from API call to Room Database
- Allow data to be viewed offline while internet is down
- Passing params of type list to another screen
- How to properly use Top App Bar for creating back arrow for navigation
- How to create Entity tables and Database for storage

https://github.com/user-attachments/assets/3a5c9dc4-607a-4395-ba98-7282b1e1773d



## Notes to Self:
- When working with navigation ensure that **AppNavHost** is in **MainActivity** and you pass navController to it
- When you get error like this(below) the first thing to check is your **navController.navigate** method and params that you pass to it to ensure names are proper and no mistakes in param values
> java.lang.IllegalArgumentException: Cannot navigate to **NavDeepLinkRequest**{ uri=android-app://androidx.navigation/**recipeDetailsScreen?nameOfDish=Classic Margherita Pizza&ingredients=Pizza dough,Tomato sauce,Fresh mozzarella cheese,Fresh basil leaves,Olive oil,Salt and pepper to taste&imageURL=https://cdn.dummyjson.com/recipe-images/1.webp**} 

- When passing arguments to another screen like this(below) make sure the Screen you're navigating from has exact same name:
```
          composable(
            route = Route.RECIPE_DETAILS_SCREEN + "?nameOfDish={nameOfDish}&ingredients={ingredients}&imageURL={imageURL}",   
            arguments = listOf(
                navArgument("nameOfDish") { type = NavType.StringType },
                navArgument("ingredients") { type = NavType.StringType },
                navArgument("imageURL") { type = NavType.StringType }
            )
        ){


      //Screen containing the navigate method will only contain the values them selves not the curly braces("{}")
                navController.navigate(
                                            Route.RECIPE_DETAILS_SCREEN +
                                            //nameOfDish, ingredients, imageURL
                                            "?nameOfDish=$nameOfDish" // the value only
                                            +"&ingredients=$ingredientsListAsString" // the value only
                                            +"&imageURL=$imageURL" // the value only
                                        )
...

 ```









