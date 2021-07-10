package com.example.gathr


import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.gathr.databinding.ActivityCallBinding
import org.jitsi.meet.sdk.*
import timber.log.Timber
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL

class CallActivity : AppCompatActivity() {

    lateinit var binding: ActivityCallBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)


        try {
            // object creation of JitsiMeetConferenceOptions
            // class by the name of options
            val options = JitsiMeetConferenceOptions.Builder()
                .setServerURL(URL(""))
                .setWelcomePageEnabled(false)
                .build()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        try {
            val roomID = intent.extras?.get("id").toString()
            calling(roomID)
        }catch (e:Exception){
            print(e.stackTrace)
        }
    }

    private fun calling(text:String) {
        // if user has typed some text in
        // EditText then only room will create
        if (text.length >=6) {
            // creating a room using JitsiMeetConferenceOptions class
            // here .setRoom() method will set the text in room name
            // here launch method with launch a new room to user where
            // they can invite others too.
            val options = JitsiMeetConferenceOptions.Builder()
                .setRoom(text)
                .build()
            JitsiMeetActivity.launch(this, options)
        }
    }

}
