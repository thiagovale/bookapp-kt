package com.example.bookapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.bookapp.dao.BookDao
import com.example.bookapp.models.Book

class BookRegistrationActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var publisherEditText: EditText
    private lateinit var genreEditText: EditText
    private lateinit var coverImageEditText: EditText
    private lateinit var saveBookButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_registration)

        titleEditText = findViewById(R.id.titleEditText)
        publisherEditText = findViewById(R.id.publisherEditText)
        genreEditText = findViewById(R.id.genreEditText)
        coverImageEditText = findViewById(R.id.coverImageEditText)
        saveBookButton = findViewById(R.id.saveBookButton)

        saveBookButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val publisher = publisherEditText.text.toString()
            val genre = genreEditText.text.toString()
            val coverImage = coverImageEditText.text.toString()

            val book = Book(
                title = title,
                publisher = publisher,
                genre = genre,
                coverImage = coverImage,
                userId = 1 // teste
            )
            val bookDao = BookDao(this)
            bookDao.addBook(book)

            finish()
        }
    }
}
