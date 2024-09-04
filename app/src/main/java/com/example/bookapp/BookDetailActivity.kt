package com.example.bookapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bookapp.dao.BookDao
import com.example.bookapp.models.Book

class BookDetailActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var publisherTextView: TextView
    private lateinit var genreTextView: TextView
    private lateinit var coverImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        titleTextView = findViewById(R.id.titleTextView)
        publisherTextView = findViewById(R.id.publisherTextView)
        genreTextView = findViewById(R.id.genreTextView)
        coverImageView = findViewById(R.id.coverImageView)

        val bookId = intent.getIntExtra("BOOK_ID", -1)

        val bookDao = BookDao(this)
        val book = bookDao.getBookById(bookId)

        if (book != null) {
            titleTextView.text = book.title
            publisherTextView.text = book.publisher
            genreTextView.text = book.genre
            // usar Glide para carregar a imagem de capa
        }
    }
}
