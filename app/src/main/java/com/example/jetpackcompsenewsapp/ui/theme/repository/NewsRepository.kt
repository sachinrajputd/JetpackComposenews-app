package com.example.jetpackcompsenewsapp.ui.theme.repository

import com.example.jetpackcompsenewsapp.data.datasource.NewsDataSource
import com.example.jetpackcompsenewsapp.data.entity.NewsResponse
import com.example.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

//In Kotlin, Flow is a cold asynchronous stream that is part of Kotlin's Coroutines API.
// It is designed to handle a stream of data asynchronously,
// making it particularly suitable for handling continuous data updates, reactive programming,
// and asynchronous operations. newsapi.org you will be get news api there

// we are using flow here to handle Api response

/*
class NewsRepository @Inject constructor(
    private val newsDataSource:NewsDataSource
){

    //flow to handle api response
   suspend  fun getNewsHeadline(country:String):Flow<ResourceState<NewsResponse>>{

       return flow {
           emit(ResourceState.Loading())

           val response = newsDataSource.getNewsHeadline(country)
           if (response.isSuccessful && response.body() != null){
               emit(ResourceState.Success(response.body()!!))// add !! becouse we alredy check body is not null
           }else{
               emit(ResourceState.Error("Error fetching news data"))
           }
       }.catch {e->
           emit(ResourceState.Error(e?.localizedMessage?:"some error in flow"))
       }

   }
}*/
class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {
    // Flow to handle API response
    suspend fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>> = flow {
        emit(ResourceState.Loading()) // Emit loading state

        try {
            val response = newsDataSource.getNewsHeadline(country)

            if (response.isSuccessful && response.body() != null) {
                // Emit success state with the response body
                emit(ResourceState.Success(response.body()!!))
            } else {
                // Emit error state with the error message
                emit(ResourceState.Error("Error fetching news data: ${response.message()}"))
            }
        } catch (e: Exception) {
            // Catch any exceptions and emit an error state
            emit(ResourceState.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}

