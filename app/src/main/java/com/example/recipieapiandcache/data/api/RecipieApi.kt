package com.example.recipieapiandcache.data.api

import com.example.recipieapiandcache.data.model.RecipiesModel
import retrofit2.http.GET

interface RecipieApi {
    @GET(ApiDetails.ENDPOINT_RECIPIES)
    suspend fun getAllRecipies(): RecipiesModel
}