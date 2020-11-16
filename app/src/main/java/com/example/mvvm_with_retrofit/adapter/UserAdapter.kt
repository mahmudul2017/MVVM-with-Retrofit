package com.example.mvvm_with_retrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_with_retrofit.R
import com.example.mvvm_with_retrofit.databinding.ItemCountryListBinding
import com.example.mvvm_with_retrofit.model.data.UserModel
import kotlinx.android.extensions.LayoutContainer

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var mList: List<UserModel>? = listOf()

    fun setData(list: List<UserModel>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemCountryListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_country_list,
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.itemBinding.userPost = mList!![position]

      /*holder.itemBinding.titleTv1.text = "UserID: " + mList!!.get(position).userId.toString()
        holder.itemBinding.titleTv2.text = "ID: " + mList!!.get(position).id.toString()
        holder.itemBinding.titleTv3.text = "Title: " + mList!!.get(position).title
        holder.itemBinding.titleTv4.text = "Body: " + mList!!.get(position).body*/
    }

    class ViewHolder(var itemBinding: ItemCountryListBinding) :
        RecyclerView.ViewHolder(itemBinding.root), LayoutContainer {
        override val containerView: View?
            get() = itemBinding.root
    }
}
