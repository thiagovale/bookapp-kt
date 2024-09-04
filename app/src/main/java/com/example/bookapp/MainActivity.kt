package com.example.bookapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRegisterUser: Button = findViewById(R.id.btnRegisterUser)
        val btnRegisterBook: Button = findViewById(R.id.btnRegisterBook)
        val btnViewBooks: Button = findViewById(R.id.btnViewBooks)

        btnRegisterUser.setOnClickListener {
            val intent = Intent(this, UserRegistrationActivity::class.java)
            startActivity(intent)
        }

        btnRegisterBook.setOnClickListener {
            val intent = Intent(this, BookRegistrationActivity::class.java)
            startActivity(intent)
        }

        btnViewBooks.setOnClickListener {
            val intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)
        }
    }
}
