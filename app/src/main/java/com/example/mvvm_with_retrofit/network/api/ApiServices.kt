package com.example.mvvm_with_retrofit.network.api

import com.example.mvvm_with_retrofit.model.data.UserModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("posts")
    fun getUserPost(): Call<List<UserModel>>
}