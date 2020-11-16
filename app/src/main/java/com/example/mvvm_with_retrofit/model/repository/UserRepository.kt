package com.example.mvvm_with_retrofit.model.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvm_with_retrofit.`interface`.NetworkResponseCallback
import com.example.mvvm_with_retrofit.model.data.UserModel
import com.example.mvvm_with_retrofit.network.client.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor() {
    private lateinit var mCallback: NetworkResponseCallback
    private var mCountryList: MutableLiveData<List<UserModel>> =
        MutableLiveData<List<UserModel>>().apply { value = emptyList() }

    companion object {
        private var mInstance: UserRepository? = null
        fun getInstance(): UserRepository {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance = UserRepository()
                }
            }
            return mInstance!!
        }
    }


    private lateinit var mCountryCall: Call<List<UserModel>>

    fun getCountries(callback: NetworkResponseCallback, forceFetch : Boolean): MutableLiveData<List<UserModel>> {
        mCallback = callback
        if (mCountryList.value!!.isNotEmpty() && !forceFetch) {
            mCallback.onNetworkSuccess()
            return mCountryList
        }
        mCountryCall = RestClient.getInstance().getApiService().getUserPost()
        mCountryCall.enqueue(object : Callback<List<UserModel>> {

            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                mCountryList.value = response.body()
                mCallback.onNetworkSuccess()
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                mCountryList.value = emptyList()
                mCallback.onNetworkFailure(t)
            }
        })
        return mCountryList
    }
}