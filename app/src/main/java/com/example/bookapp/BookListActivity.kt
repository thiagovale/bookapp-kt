package com.example.bookapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.dao.BookDao
import com.example.bookapp.models.Book

class BookListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val bookDao = BookDao(this)
        val userId = 1  // teste
        val books = bookDao.getBooksByUserId(userId)

        bookListAdapter = BookListAdapter(books) { book ->
            val intent = Intent(this, BookDetailActivity::class.java)
            intent.putExtra("BOOK_ID", book.id)
            startActivity(intent)
        }
        recyclerView.adapter = bookListAdapter
    }
}
