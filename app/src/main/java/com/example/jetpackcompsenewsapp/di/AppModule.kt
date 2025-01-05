package com.example.jetpackcompsenewsapp.di

import com.example.jetpackcompsenewsapp.data.AppConstant
import com.example.jetpackcompsenewsapp.data.api.ApiService
import com.example.jetpackcompsenewsapp.data.datasource.NewsDataSource
import com.example.jetpackcompsenewsapp.data.datasource.NewsDataSourceImpl
import com.example.jetpackcompsenewsapp.ui.theme.repository.NewsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// its simple proccess in order define module in dependacy injection using daggar hilt
// if our application making any api call we should able to see log what is status
// , what is response we are getting, using Httplogin interceptor we can use scope fun apply
// we are also using httpclient to associte with our retrofit add timeer,save cache etc..
// moshi we are using to convert data like gson library
// we added @Provides @Singleton anotaion thise means we can use fun any where in project use dagger
//created apiservice dependacy that provde any where fun provideApiService
//uses of @inject
//Use @Inject on constructors to enable Dagger to create instances of classes.
//Use @Inject on fields to inject dependencies into Android components.
//Use @Inject on methods for post-construction dependency injection.
//Dagger integrates seamlessly with @Inject to provide a robust DI framework that ensures
// dependencies are provided efficiently and safely.
// aded dependacy provider in the AppModule for use access class fun
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC

        }
        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }
        httpClient.apply {
            readTimeout(60, TimeUnit.SECONDS)
        }
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl(AppConstant.APP_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)

    }

    @Provides
    @Singleton
    fun providesNewsDataSource(apiService: ApiService): NewsDataSource{

        return NewsDataSourceImpl(apiService)

    }
    @Provides
    @Singleton

    fun providesNewsRepository(newsDataSource: NewsDataSource):NewsRepository{

        return NewsRepository(newsDataSource)
    }
}