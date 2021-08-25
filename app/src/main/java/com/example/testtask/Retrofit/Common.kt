package com.example.testtask.Retrofit

object Common {
    private val BASE_URL = "https://api.github.com/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getData(BASE_URL).create(RetrofitServices::class.java)
}