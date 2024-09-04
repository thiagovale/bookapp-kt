package com.example.bookapp.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.bookapp.DbHelper
import com.example.bookapp.models.Book

class BookDao(private val context: Context) {
    private val dbHelper = DbHelper(context)

    fun addBook(book: Book): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DbHelper.COLUMN_BOOK_TITLE, book.title)
            put(DbHelper.COLUMN_BOOK_PUBLISHER, book.publisher)
            put(DbHelper.COLUMN_BOOK_GENRE, book.genre)
            put(DbHelper.COLUMN_BOOK_COVER_IMAGE, book.coverImage)
            put(DbHelper.COLUMN_USER_ID, book.userId)
        }
        return db.insert(DbHelper.TABLE_BOOK, null, values)
    }

    fun getBooksByUserId(userId: Int): List<Book> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DbHelper.TABLE_BOOK, null,
            "${DbHelper.COLUMN_USER_ID} = ?", arrayOf(userId.toString()),
            null, null, null
        )
        return cursorToBooks(cursor)
    }

    fun getBookById(bookId: Int): Book? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DbHelper.TABLE_BOOK, null,
            "${DbHelper.COLUMN_BOOK_ID} = ?", arrayOf(bookId.toString()),
            null, null, null
        )
        return cursorToBook(cursor)
    }

    private fun cursorToBooks(cursor: Cursor): List<Book> {
        val books = mutableListOf<Book>()
        if (cursor.moveToFirst()) {
            do {
                val bookId = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BOOK_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BOOK_TITLE))
                val publisher = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BOOK_PUBLISHER))
                val genre = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BOOK_GENRE))
                val coverImage = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BOOK_COVER_IMAGE))
                val userId = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_USER_ID))
                books.add(Book(bookId, title, publisher, genre, coverImage, userId))
            } while (cursor.moveToNext())
        }
        return books
    }

    private fun cursorToBook(cursor: Cursor): Book? {
        if (cursor.moveToFirst()) {
            val bookId = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BOOK_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BOOK_TITLE))
            val publisher = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BOOK_PUBLISHER))
            val genre = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BOOK_GENRE))
            val coverImage = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BOOK_COVER_IMAGE))
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_USER_ID))
            return Book(bookId, title, publisher, genre, coverImage, userId)
        }
        return null
    }
}
