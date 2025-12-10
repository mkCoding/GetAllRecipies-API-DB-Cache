package com.example.recipieapiandcache

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.recipieapiandcache.presentation.navigation.AppNavGraph
import com.example.recipieapiandcache.presentation.screens.RecipieScreen
import com.example.recipieapiandcache.presentation.screens.RecipieViewModel
import com.example.recipieapiandcache.ui.theme.RecipieAPIAndCacheTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipieAPIAndCacheTheme {

                // Add VM and state
                val viewModel: RecipieViewModel = viewModel()
                val state by viewModel.recipeState.collectAsState()

                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipieAPIAndCacheTheme {
        Greeting("Android")
    }
}