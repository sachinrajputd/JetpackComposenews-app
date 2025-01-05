package com.example.jetpackcompsenewsapp.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackcompsenewsapp.ui.theme.component.Loader
import com.example.jetpackcompsenewsapp.ui.theme.component.NewsList
import com.example.jetpackcompsenewsapp.ui.theme.component.NewsRowComponent
import com.example.jetpackcompsenewsapp.ui.theme.viewmodel.NewViewModel
import com.example.utilities.ResourceState


//In software development, particularly in Android development and reactive programming
// frameworks like Jetpack Compose, State refers to a value or object that holds information
// about the current situation or status of a UI component or application at a given point in time.


// **collectAsState** function is used to convert a Flow or StateFlow
// into a Compose State object. This allows the UI to automatically recompose whenever the
// Flow emits a new value,
// making it easier to handle reactive data streams in a composable function.

const val TAG = "HomeScreen"
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    newsViewModel: NewViewModel= hiltViewModel()
){
// setting up News list in to HomeScreen
    // created with pager
   // val newsRes = newsViewModel.news.collectAsState()
    val newsRes by newsViewModel.news.collectAsState()
    val pagerState = rememberPagerState (
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ){
        100
    }
    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp) {page : Int->

        Log.d(TAG,"InsideHomeScreen")

            // handling response
            // Log.d(TAG,"newsRes ${newsRes}")
            when(newsRes){
                //as per video

                is ResourceState.Loading -> {
                    Log.d(TAG,"Inside_Loading")
                    Loader()
                }
                is ResourceState.Success -> {

                    // here we are typecsting
                    val response = (newsRes as ResourceState.Success).data
                    Log.d(TAG,"Inside_Success ${response.status} ${response.totalResults}")
                   // NewsList(response,page)
                    if (response.articles.isNotEmpty()) {

                        NewsRowComponent(page, response.articles.get(page))
                    }
                }
                is ResourceState.Error -> {
                    val error = (newsRes as ResourceState.Error)
                    Log.d(TAG,"Inside_Error $error")

                }

        }
        
    }

}
@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}
