package com.example.jetpackcompsenewsapp

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.jetpackcompsenewsapp.ui.theme.navigation.AppNavigationGraph
import com.example.jetpackcompsenewsapp.ui.theme.JetpackCompseNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
// added entry point in mainactivity to considere this file also inject with hilt
// this code created waching video for native mobile bits
// video name : Building a Complete Android App : Jetpack Compose, MVVM, Coroutines & Dependency Injection

//Building a Complete Android App : Jetpack Compose, MVVM, Coroutines
// & Dependency Injection
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       // InstallSplashScreen()
       // val splashScreen = SplashScreen.SPLASH_SCREEN_STYLE_SOLID_COLOR
        setContent {
            JetpackCompseNewsAppTheme {
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)) {


                    AppEntryPoint()

                }

            }
        }
    }
}
@Composable
fun AppEntryPoint(){
    AppNavigationGraph()



}