package com.example.jetpackcompsenewsapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

// created hilt in androidapp  and added in manifest  android:name=".NewsApplication"
@HiltAndroidApp
class NewsApplication : Application() {
    @Override
    override fun onCreate() {
        super.onCreate()
    Log.d(TAG,"coming inside onCreate")
    }
    companion object{
      const  val TAG= "NewsApplication"
    }


}