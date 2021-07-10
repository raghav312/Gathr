package com.example.gathr.adapters

import android.view.View
import com.example.gathr.R
import com.example.gathr.databinding.LatestMessagesRowBinding
import com.example.gathr.models.ChatMessage
import com.example.gathr.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.viewbinding.BindableItem

class LatestMessageRow(val chatMessage: ChatMessage):BindableItem<LatestMessagesRowBinding>() {
    var chatPartnerUser: User? = null

    override fun bind(viewBinding: LatestMessagesRowBinding, position: Int) {

        viewBinding.tvSenderLastMessage.text = chatMessage.text
        val chatPartnerId:String
        if(chatMessage.fromId == FirebaseAuth.getInstance().uid){
            chatPartnerId = chatMessage.toId
        }else{
            chatPartnerId = chatMessage.fromId
        }

        val ref  = FirebaseDatabase.getInstance().getReference("/users/$chatPartnerId")
        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                chatPartnerUser = snapshot.getValue(User::class.java)
                viewBinding.tvSenderName.text = chatPartnerUser?.username
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    override fun getLayout(): Int {
        return R.layout.latest_messages_row
    }

    override fun initializeViewBinding(view: View): LatestMessagesRowBinding {
        return LatestMessagesRowBinding.bind(view)
    }
}