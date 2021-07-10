package com.example.gathr.adapters

import android.view.View
import com.example.gathr.R
import com.example.gathr.databinding.ChatFromRowBinding
import com.example.gathr.models.User
import com.xwray.groupie.viewbinding.BindableItem

class ChatFromItem(val text:String, val user: User): BindableItem<ChatFromRowBinding>() {
    override fun bind(viewBinding: ChatFromRowBinding, position: Int) {
        viewBinding.tvFromRow.text = text
        viewBinding.tvFromItemName.text = user.username.toString()
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }

    override fun initializeViewBinding(view: View): ChatFromRowBinding {
        return ChatFromRowBinding.bind(view)
    }
}