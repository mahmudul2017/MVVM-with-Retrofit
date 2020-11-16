package com.example.mvvm_with_retrofit.model.data

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)