package com.syed.loginsign_up

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.syed.loginsign_up.databinding.ActivitySingupBinding

class SingupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingupBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupBtn.setOnClickListener{
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){ task ->
                    if (task.isSuccessful){
                    Toast.makeText(this,"Sing up Succesfull",Toast.LENGTH_SHORT).show()
                    val  intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    }else{
                    Toast.makeText(this,"Sign-up Unsuccessfull",Toast.LENGTH_SHORT ).show()
                    }
                }
            }else{
                Toast.makeText(this,"Please Enter the required field.",Toast.LENGTH_SHORT).show()
            }
        }
        binding.logTv.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}