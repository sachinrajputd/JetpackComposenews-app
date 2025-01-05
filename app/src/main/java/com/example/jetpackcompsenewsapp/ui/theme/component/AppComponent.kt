package com.example.jetpackcompsenewsapp.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetpackcompsenewsapp.data.entity.Article
import com.example.jetpackcompsenewsapp.data.entity.NewsResponse


// implement custome composable
// AsyncImage its comming throw the coil liabray

@Composable
fun Loader(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp),
            color = Color.Black

        )
    }
}

// implement list jetpack compose
@Composable
fun NewsList(response: NewsResponse){
    LazyColumn {
         items(response.articles){ articles ->
             NormalTextComponent(textValue = articles.title?:"NA")

         }
    }
}
@Composable
fun NewsRowComponent(page:Int,article: Article){

   // NormalTextComponent(textValue = "$page \n\n ${article.title}")


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(Color.White)
    ) {
        // AsyncImage its comming throw the coil liabray
        AsyncImage(modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
            model = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.size(20.dp))

        HeadingTextComponent(textValue = article.title?:"")

        Spacer(modifier = Modifier.size(10.dp))
        
   //     NormalTextComponent(textValue = article.content?:"")

        NormalTextComponent(textValue = article.description?:"")
        
        AuthorDetailsComponent(article.author, article.source?.name )

    }

}
@Composable
fun NormalTextComponent(textValue: String){
    Text(modifier = Modifier
        .fillMaxSize()
        .wrapContentHeight()
        .padding(8.dp),text = textValue,
        style = TextStyle(fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    )
}
@Composable
fun HeadingTextComponent(textValue: String){
    Text(modifier = Modifier
        .fillMaxSize()
        .wrapContentHeight()
        .padding(8.dp),text = textValue,
        style = TextStyle(fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    )
}

@Composable
fun AuthorDetailsComponent(authorName:String? ,sourceName:String? ){
 Row (modifier = Modifier.fillMaxWidth()){

     authorName?.also {
         Text(text = it)
     }
     Spacer(modifier = Modifier.weight(1f))
     sourceName?.also {
         Text(text = it)

     }






 }

}