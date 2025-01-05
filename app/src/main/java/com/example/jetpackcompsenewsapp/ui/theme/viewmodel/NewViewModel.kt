package com.example.jetpackcompsenewsapp.ui.theme.viewmodel

import  android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompsenewsapp.data.AppConstant
import com.example.jetpackcompsenewsapp.data.entity.NewsResponse
import com.example.jetpackcompsenewsapp.ui.theme.repository.NewsRepository
import com.example.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// implemented MVVM
// this is simple how to define the viewmodel
// we are also using dependecy injection here anotating with HiltViewmodel
// to acces newviewmodel we should have one inject constructor
// hilt will be inject at runtime

// here is we are linking viewmodel with repository

// we can only call suspending fun only from in coroutine
// its asyncrononce programming  viewModelScope.launch(Dispatchers.IO)
//collectLatest block its use to collect flow

//init block is a special block that is executed when
// an object of a class is created. It is used to perform initialization logic for the class.
//The init block is part of the primary constructor, and it is executed immediately
// after the primary constructor is invoked.
@HiltViewModel
class NewViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    
    private val _news: MutableStateFlow<ResourceState<NewsResponse>> =
        MutableStateFlow(ResourceState.Loading())
    val news: StateFlow<ResourceState<NewsResponse>> = _news

    init {
        getNews(AppConstant.COUNTRY)
    }

    private fun getNews(country: String) {
        viewModelScope.launch {
            try {
                // Collecting flow from repository
                newsRepository.getNewsHeadline(country)
                    .collectLatest { newsResponse ->
                        // Ensure UI updates happen on Main thread
                        withContext(Dispatchers.Main) {
                            _news.value = newsResponse
                        }
                    }
            } catch (e: Exception) {
                // Handle unexpected errors and emit Error state
                Log.e(TAG, "Error in getNews: ${e.localizedMessage}")
                _news.value = ResourceState.Error("Unexpected error: ${e.localizedMessage}")
            }
        }
    }

    companion object {
        const val TAG = "NewViewModel"
    }
}
