package com.my.numberscompose.data.apiInterface

import com.my.numberscompose.data.model.ModelNumberFromRetrofit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("random/math?json")
    suspend fun getRandomInfo(): Response<ModelNumberFromRetrofit>

    @GET("{number}/math?json")
    suspend fun getInfoNumber(@Path("number") num: String): Response<ModelNumberFromRetrofit>
}