package com.bombadu.aprikot.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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