package com.example.gathr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gathr.adapters.UserItem
import com.example.gathr.databinding.ActivityNewMessageBinding
import com.example.gathr.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class NewMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Select User"

        //fetch all the users
        fetchUsers()
    }

    //finish the activity when user press back button
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    //fetch users from realtime db and use that to make an adapter and put it into
    //the view

    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()
                p0.children.forEach {
                    Log.d("NewMessage", it.toString())
                    val user = it.getValue(User::class.java)
                    //dont get the data of user itself
                    if (user != null && user.uid!=FirebaseAuth.getInstance().currentUser?.uid ) {
                        adapter.add(UserItem(user) )
                    }
                }

                //go to compose a new message when clicked
                adapter.setOnItemClickListener { item, view ->
                    val userItem = item as UserItem
                    val intent = Intent(view.context, ChatActivity::class.java)
                    intent.putExtra(USER_KEY, userItem.user)
                    startActivity(intent)
                    finish()
                }

                binding.rvComposeChat.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {
                finish()
            }
        })
    }


    //static variables
    companion object{
        const val USER_KEY = "USER_KEY"
    }


}