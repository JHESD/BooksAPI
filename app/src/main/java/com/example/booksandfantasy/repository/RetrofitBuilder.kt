package com.example.booksandfantasy.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val RetrofitBuilderWows = Retrofit.Builder()
        .baseUrl("http://apilibreria.jmacboy.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}