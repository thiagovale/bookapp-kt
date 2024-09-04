package com.example.bookapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.bookapp.dao.UserDao
import com.example.bookapp.models.User

class UserRegistrationActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var profileImageEditText: EditText
    private lateinit var saveUserButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        profileImageEditText = findViewById(R.id.profileImageEditText)
        saveUserButton = findViewById(R.id.saveUserButton)

        saveUserButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val profileImage = profileImageEditText.text.toString()

            val user = User(name = name, email = email, profileImage = profileImage)
            val userDao = UserDao(this)
            userDao.addUser(user)

            finish()
        }
    }
}
