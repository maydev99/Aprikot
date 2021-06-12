package com.bombadu.aprikot.network

import com.bombadu.aprikot.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MealDbApi {

    @GET("/api/json/v1/1/categories.php")
    suspend fun getCategories() : CategoryData

    @GET("/api/json/v1/1/filter.php?")
    suspend fun getRecipesByCategory(
        @Query("c") category: String
    ) : RecipesData

    @GET("/api/json/v1/1/lookup.php?")
    suspend fun getPreparationData(
        @Query("i") preparation: String
    ) : PreparationData
}

object Network {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
         level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: MealDbApi by lazy {
        retrofit.create(MealDbApi::class.java)
    }
}