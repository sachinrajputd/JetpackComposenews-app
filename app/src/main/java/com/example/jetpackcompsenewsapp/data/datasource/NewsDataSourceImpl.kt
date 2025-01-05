package com.example.jetpackcompsenewsapp.data.datasource

import com.example.jetpackcompsenewsapp.data.api.ApiService
import com.example.jetpackcompsenewsapp.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

// need to call api here that why we inject on constructor method

class NewsDataSourceImpl  @Inject constructor(
    private val apiService: ApiService
): NewsDataSource {
    override suspend fun getNewsHeadline(country: String): Response<NewsResponse> {
        return apiService.getNewsHeadline(country)

    }

}