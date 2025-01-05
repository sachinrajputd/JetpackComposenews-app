package com.example.jetpackcompsenewsapp.data.datasource

import com.example.jetpackcompsenewsapp.data.entity.NewsResponse
import retrofit2.Response

// here we used coroutine to suspend the fun
// A suspend function is a special type of function that can be paused and
// resumed at a later time without blocking the thread it's running on.

interface NewsDataSource {


  suspend  fun getNewsHeadline(country:String) : Response<NewsResponse>


}