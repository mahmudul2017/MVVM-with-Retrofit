package com.example.mvvm_with_retrofit.`interface`

interface NetworkResponseCallback {
    fun onNetworkSuccess()
    fun onNetworkFailure(th : Throwable)
}