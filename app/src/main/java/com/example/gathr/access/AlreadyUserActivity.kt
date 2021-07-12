package com.example.gathr.access

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.gathr.MainActivity
import com.example.gathr.databinding.ActivityAlreadyUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException


//When already have an account
class AlreadyUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlreadyUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlreadyUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvBTR.setOnClickListener {
            finish()
        }
        binding.btnLogin.setOnClickListener {
            performLogin()
        }
    }

    //Perform login by access the firebase realtime database

    private fun performLogin() {
        val email = binding.etUserEmail.text.toString()
        val password = binding.etUserPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill out email/pw.", Toast.LENGTH_SHORT).show()
            return
        }

        //get firebase instance and check email and password
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                try {
                    //success in logging in
                    //go to main activity
                    if (!it.isSuccessful) return@addOnCompleteListener
                    Log.d("Login", "Successfully logged in: ${it.result?.user?.uid}")
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }catch(e:FirebaseAuthInvalidUserException){
                    //Exception Email does not exist
                    Toast.makeText(this, "Email does not exist.", Toast.LENGTH_SHORT).show()
                }catch(e:FirebaseAuthInvalidCredentialsException){
                    //Exception wronf creds
                    Toast.makeText(this, "Wrong password, try again or create a new account.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                //any other failure
                Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}