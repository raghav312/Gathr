package com.example.gathr


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gathr.databinding.ActivityCallBinding
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.MalformedURLException
import java.net.URL

class CallActivity : AppCompatActivity() {

    lateinit var binding: ActivityCallBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val roomID = intent.extras?.get("id").toString()
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
        calling(roomID)
    }

    private fun calling(text:String) {

        // if user has typed some text in
        // EditText then only room will create
        if (text.length >=6) {
            // creating a room using  JitsiMeetConferenceOptions class
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
