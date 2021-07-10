package com.example.gathr.access

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.gathr.MainActivity
import com.example.gathr.databinding.ActivityLoginBinding
import com.example.gathr.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener { performRegister() }
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, AlreadyUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performRegister() {

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d(TAG, "Attempting to create user with email: $email")


            // Firebase Authentication to create a user with email and password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    try {
                        if (!it.isSuccessful) return@addOnCompleteListener
                    }catch (e:FirebaseAuthUserCollisionException){
                        Toast.makeText(this, "User already exist!", Toast.LENGTH_SHORT).show()
                    }catch(e:FirebaseAuthInvalidCredentialsException){
                        Toast.makeText(this, "Email malformed", Toast.LENGTH_SHORT).show()
                    }catch (e:FirebaseAuthWeakPasswordException){
                        Toast.makeText(this, "Weak password exception", Toast.LENGTH_SHORT).show()
                    }
                    // else if successful
                    lifecycleScope.launch(Dispatchers.IO) { saveUserToFirebaseDatabase() }
                    Log.d(TAG, "Successfully created user with uid: ${it.result?.user?.uid}")

                }
                .addOnFailureListener {
                    Log.d(TAG, "Failed to create user: ${it.message}")
                    Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }


    }

    private fun saveUserToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, binding.etName.text.toString())

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d(TAG, "Finally we saved the user to Firebase Database")

                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to set value to database: ${it.message}")
            }
    }

}