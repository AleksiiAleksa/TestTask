package com.example.testtask.Retrofit

import com.example.testtask.Data
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("repositories")
    fun getRecords():Call<MutableList<Data>>

}