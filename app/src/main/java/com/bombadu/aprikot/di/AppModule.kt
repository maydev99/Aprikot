package com.bombadu.aprikot.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    /*@Singleton
    @Provides
    fun provideMealDbApi() : MealDbApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealDbApi::class.java)
    }*/

   /* @Singleton
    @Provides
    fun provideMainRepository(
        api: MealDbApi
    ) = DefaultMainRepository(api) as MainRepository*/




}