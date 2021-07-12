package com.example.gathr.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gathr.CallActivity
import com.example.gathr.R
import com.example.gathr.databinding.FragmentCallBinding


//Call fragment
class CallFragment : Fragment(), View. OnClickListener {

    private lateinit var binding: FragmentCallBinding
    private lateinit var roomID: String

    companion object{
        const val ADDER:String = " Come on lets Gathr! Join the call with this GathrID "
        const val GATHR_ID_LEN = 8
    }

    //triggered on fragment creation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCallBinding.inflate(layoutInflater)
        val view:View? = binding.root
        // Inflate the layout for this fragment
        binding.btnJoinCall.setOnClickListener(this)
        binding.btnCopyID.setOnClickListener(this)
        binding.tvIDGen.setOnClickListener(this)
        binding.btnShareCall.setOnClickListener(this)
        return view
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnShareCall->{
                //share call
                roomID = binding.etRoomID.text.toString()
                if(checkRoomId()){
                    shareRoomID(ADDER+roomID)
                }else{
                    Toast.makeText(requireContext(),"Please Enter a valid gathrID",Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btnJoinCall ->{ // make a connect to call request
                roomID = binding.etRoomID.text.toString()
                if(checkRoomId()){
                    val intent = Intent(requireContext(),CallActivity::class.java)
                    intent.putExtra("id",roomID)
                    startActivity(intent)
                }else{
                    Toast.makeText(requireContext(),"Please Enter a valid gathrID",Toast.LENGTH_SHORT).show()
                }

            }
            R.id.tvIDGen -> {
                // generate the 6 digit alpha numeric and display the updated UI
                val generatedString = getRandomString(GATHR_ID_LEN)
                binding.tvGeneratedID.text = generatedString
                binding.llGenerator.visibility = View.VISIBLE
                roomID = generatedString
            }
            R.id.btnCopyID -> {
                // set room ID to be generated RoomID
                binding.etRoomID.setText(roomID)
            }

        }
    }

    //generate a random alphanumeric string of desired length
    private fun getRandomString(length:Int) : String{
        val allowChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        val s:String = (1..length).map{allowChars.random()}.joinToString("")
        println(s)
        return s
    }

    //share the roomID
    private fun shareRoomID(roomID:String){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT , roomID)
        startActivity(Intent.createChooser(intent , "Share via"))
    }

    //check roomID
    private fun checkRoomId(): Boolean {
        return roomID.length >= 6
    }


}