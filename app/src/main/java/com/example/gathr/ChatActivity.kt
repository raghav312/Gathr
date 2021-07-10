package com.example.gathr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gathr.adapters.ChatFromItem
import com.example.gathr.adapters.ChatToItem
import com.example.gathr.databinding.ActivityChatBinding
import com.example.gathr.fragments.ChatFragment
import com.example.gathr.models.ChatMessage
import com.example.gathr.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ChatActivity : AppCompatActivity() {

    private lateinit var binding:ActivityChatBinding
    val adapter = GroupAdapter<GroupieViewHolder>()
    var toUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvChatlog.adapter = adapter
        toUser = intent.extras?.getSerializable(NewMessageActivity.USER_KEY) as User

        listenForMessages()
        binding.btnSendText.setOnClickListener {
            Log.d(TAG, "Attempt to send message....")
            performSendMessage()
        }
    }

    private fun listenForMessages() {

        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")

        ref.addChildEventListener(object: ChildEventListener {

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)
                if (chatMessage != null) {
                    Log.d(TAG, chatMessage.text)

                    if(chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        val currentUser = ChatFragment.currentUser ?: return
                        adapter.add(ChatToItem(chatMessage.text, currentUser))
                    }else{
                        adapter.add(ChatFromItem(chatMessage.text, toUser!!))
                    }
                }

                binding.rvChatlog.scrollToPosition(adapter.itemCount - 1)

            }

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

        })

    }


    private fun performSendMessage() {
        // how do we actually send a message to firebase...
        val text = binding.etChatLog.text.toString()

        val fromId = FirebaseAuth.getInstance().uid
        val user:User? = intent.extras?.getSerializable(NewMessageActivity.USER_KEY)  as User
        val toId = user?.uid

        if (fromId == null) return

        val reference = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()
        val toReference = FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()
        val chatMessage = toId?.let {
            ChatMessage(reference.key!!, text, fromId,
                it, System.currentTimeMillis() / 1000)
        }

        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "Saved our chat message: ${reference.key}")
                binding.etChatLog.text.clear()
                binding.rvChatlog.scrollToPosition(adapter.itemCount - 1)
            }

        toReference.setValue(chatMessage)

        val latestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$toId")
        latestMessageRef.setValue(chatMessage)

        val latestMessageToRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$toId/$fromId")
        latestMessageToRef.setValue(chatMessage)
    }

    companion object {
        val TAG = "ChatLog"
    }

}