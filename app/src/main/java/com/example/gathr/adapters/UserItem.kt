package com.example.gathr.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.gathr.R
import com.example.gathr.databinding.UserRowNewMessageBinding
import com.example.gathr.models.User
import com.xwray.groupie.viewbinding.BindableItem

class UserItem(val user:User): BindableItem<UserRowNewMessageBinding>() {

    override fun bind(viewBinding: UserRowNewMessageBinding, position: Int) {
        viewBinding.usernameTextviewNewMessage.text = user.username.toString()
    }

    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }

    override fun initializeViewBinding(view: View): UserRowNewMessageBinding {
        return UserRowNewMessageBinding.bind(view)
    }


}