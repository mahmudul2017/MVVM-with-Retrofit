package com.example.mvvm_with_retrofit.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_with_retrofit.`interface`.NetworkResponseCallback
import com.example.mvvm_with_retrofit.model.data.UserModel
import com.example.mvvm_with_retrofit.model.repository.UserRepository
import com.example.mvvm_with_retrofit.utils.NetworkHelper

class UserViewModel(private val app: Application) : AndroidViewModel(app) {
    private var mList: MutableLiveData<List<UserModel>> = MutableLiveData<List<UserModel>>().apply { value = emptyList() }
    val mShowProgressBar = MutableLiveData(true)
    val mShowNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    val mShowApiError = MutableLiveData<String>()
    private var mRepository = UserRepository.getInstance()

    fun fetchCountriesFromServer(forceFetch: Boolean): MutableLiveData<List<UserModel>> {
        if (NetworkHelper.isOnline(app.baseContext)) {
            mShowProgressBar.value = true
            mList = mRepository.getCountries(object : NetworkResponseCallback {
                override fun onNetworkFailure(th: Throwable) {
                    mShowApiError.value = th.message
                }

                override fun onNetworkSuccess() {
                    mShowProgressBar.value = false
                }
            }, forceFetch)
        } else {
            mShowNetworkError.value = true
        }
        return mList
    }

    fun onRefreshClicked(view: View) {
        fetchCountriesFromServer(true)
    }
}