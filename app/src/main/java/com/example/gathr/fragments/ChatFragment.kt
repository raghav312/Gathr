package com.example.gathr.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.gathr.ChatActivity
import com.example.gathr.access.LoginActivity
import com.example.gathr.NewMessageActivity
import com.example.gathr.adapters.LatestMessageRow
import com.example.gathr.databinding.FragmentChatBinding
import com.example.gathr.models.ChatMessage
import com.example.gathr.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val adapter = GroupAdapter<GroupieViewHolder>()
    val latestMessagesMap = HashMap<String, ChatMessage>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater)

        //set up adapter
        binding.rvLatestMessages.adapter = adapter

        //adapter on click action
        adapter.setOnItemClickListener { item, view ->
            Log.d(TAG,"123")
            val intent = Intent(requireContext(), ChatActivity::class.java)
            val row = item as LatestMessageRow
            intent.putExtra(NewMessageActivity.USER_KEY, row.chatPartnerUser)
            startActivity(intent)
        }

        //listen for messages for user
        listenForLatestMessages()

        //listen for messages for user
        fetchCurrentUser()


        signInVerified()

        //create a new message
        binding.fabCreateNewMessage.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(requireActivity(),NewMessageActivity::class.java)
                startActivity(intent)
            }
        })
        return binding.root
    }

    //refresh recycler view and observe new messages
    private fun refreshRecyclerViewMessages() {
        adapter.clear()
        latestMessagesMap.values.forEach {
            adapter.add(LatestMessageRow(it))
        }
    }


    //get latest messages and show it in the adapter
    private fun listenForLatestMessages() {
        val fromId = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId")
        ref.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                latestMessagesMap[p0.key!!] = chatMessage
                refreshRecyclerViewMessages()
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                latestMessagesMap[p0.key!!] = chatMessage
                refreshRecyclerViewMessages()
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }
            override fun onChildRemoved(p0: DataSnapshot) {

            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }



    //get current user details
    private fun fetchCurrentUser() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                currentUser = p0.getValue(User::class.java)
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }


    //verify user if user signed or not
    private fun signInVerified(){
        val uid = FirebaseAuth.getInstance().uid
        if(uid == null){
            //if not signed in user will be redirected to login activity in access package
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    //companion object to be used across package
    companion object {
        var currentUser: User? = null
        val TAG = "LatestMessages"
    }

}