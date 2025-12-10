package com.example.recipieapiandcache.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipieapiandcache.data.local.RecipeEntity
import com.example.recipieapiandcache.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class RecipieViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel(){

    // create state to pass to UI
    private val _recipeState = MutableStateFlow<RecipeState>(RecipeState.Loading)
    val recipeState: StateFlow<RecipeState> = _recipeState

    init {
        loadRecipieData()
    }
    // create method to load data

    private fun loadRecipieData(){
        _recipeState.value = RecipeState.Loading

        viewModelScope.launch {
            try {
                // check the db first
                val dataInDB = repository.getRecipes()
                if(dataInDB.isNotEmpty()){
                    _recipeState.value = RecipeState.Success(dataInDB)
                }else{
                    _recipeState.value = RecipeState.Error("No recipes available")
                }

            }catch (e: IOException){
                _recipeState.value = RecipeState.Error("Network Error: ${e.message}")
            }catch (e: Exception){
                _recipeState.value = RecipeState.Error("Unknown Error: ${e.message}")
            }
        }
    }
}

// sealed class for states
sealed class RecipeState{
    // loading, success, error
    object Loading: RecipeState()
    data class Success(val recipieData: List<RecipeEntity>): RecipeState()
    data class Error(val message:String): RecipeState()
}