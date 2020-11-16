package com.example.mvvm_with_retrofit.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadrosid.svgloader.SvgLoader
import com.example.mvvm_with_retrofit.R
import com.example.mvvm_with_retrofit.adapter.UserAdapter
import com.example.mvvm_with_retrofit.databinding.ActivityCoutrylistBinding
import com.example.mvvm_with_retrofit.viewmodel.UserViewModel

class CountryListActivity : AppCompatActivity() {

    private lateinit var mAdapter: UserAdapter
    private lateinit var mViewModel: UserViewModel
    private lateinit var mActivityBinding: ActivityCoutrylistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_coutrylist)

        mActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_coutrylist)

        mViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        mActivityBinding.viewModel = mViewModel
        mActivityBinding.lifecycleOwner = this

        initializeRecyclerView()
        initializeObservers()
    }
    private fun initializeRecyclerView() {
        mAdapter = UserAdapter()
        mActivityBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun initializeObservers() {
        mViewModel.fetchCountriesFromServer(false).observe(this, Observer { kt ->
            mAdapter.setData(kt)
        })
        mViewModel.mShowApiError.observe(this, Observer {
            AlertDialog.Builder(this).setMessage(it).show()
        })
        mViewModel.mShowProgressBar.observe(this, Observer { bt ->
            if (bt) {
                mActivityBinding.progressBar.visibility = View.VISIBLE
                mActivityBinding.floatingActionButton.hide()
            } else {
                mActivityBinding.progressBar.visibility = View.GONE
                mActivityBinding.floatingActionButton.show()
            }
        })
        mViewModel.mShowNetworkError.observe(this, Observer {
            AlertDialog.Builder(this).setMessage(R.string.app_no_internet_msg).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        SvgLoader.pluck().close()
    }
}