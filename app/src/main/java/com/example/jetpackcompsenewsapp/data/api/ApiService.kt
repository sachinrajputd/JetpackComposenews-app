package com.example.jetpackcompsenewsapp.data.api

import com.example.jetpackcompsenewsapp.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// call API
interface ApiService {

    @GET("v2/top-headlines")
   suspend fun getNewsHeadline(
        @Query("country") country : String,
        @Query("apiKey") apiKey: String = "2514f7b8686c4f6c9f0c606736de79c0"
    ) :Response<NewsResponse>


}
//https://newsapi.org/v2/top-headlines?country=us&apiKey=YOUR_API_KEY