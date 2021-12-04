package com.soumalya.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    lateinit var etMobileNumber: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogIn: Button
    lateinit var txtForgetPassword: TextView
    lateinit var txtRegister: TextView
    val validMobileNumber = "0123456789"
    val validPassword = arrayOf("tony", "steve","bruce","thanos")
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name),Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        setContentView(R.layout.activity_login)

        if (isLoggedIn){
            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        title = "Log In"

        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        btnLogIn = findViewById(R.id.btLogIn)
        txtForgetPassword = findViewById(R.id.txtForgetPassword)
        txtRegister = findViewById(R.id.txtRegister)

        btnLogIn.setOnClickListener {

            val mobileNumber = etMobileNumber.text.toString()
            val password = etPassword.text.toString()
            var nameOfAvenger = "Avenger"
            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)

            if ((mobileNumber == validMobileNumber)){
                when (password) {
                    validPassword[0] -> {

                        nameOfAvenger = "Iron Man"

                        savePreferences(nameOfAvenger)

                        startActivity(intent)

                    }
                    validPassword[1] -> {

                        nameOfAvenger = "Captain America"

                        savePreferences(nameOfAvenger)

                        startActivity(intent)

                    }
                    validPassword[2] -> {

                        nameOfAvenger = "The Hulk"

                        savePreferences(nameOfAvenger)

                        startActivity(intent)

                    }
                    validPassword[3] -> {

                        nameOfAvenger = "The Avengers"

                        savePreferences(nameOfAvenger)

                        startActivity(intent)

                    }
                    else -> Toast.makeText(this@LoginActivity, "Incorrect Password", Toast.LENGTH_LONG).show()
                }

            } else {

                Toast.makeText(this@LoginActivity, "Incorrect Credentials", Toast.LENGTH_LONG).show()

            }

        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
    fun savePreferences(title: String){
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        sharedPreferences.edit().putString("Title", title).apply()
    }

}