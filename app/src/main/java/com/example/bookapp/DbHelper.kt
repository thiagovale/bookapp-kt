package com.example.bookapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Criando tabela de usuários
        val createUserTable = """
            CREATE TABLE $TABLE_USER (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_NAME TEXT,
                $COLUMN_USER_EMAIL TEXT,
                $COLUMN_USER_PROFILE_IMAGE TEXT
            );
        """.trimIndent()

        // Criando tabela de livros
        val createBookTable = """
            CREATE TABLE $TABLE_BOOK (
                $COLUMN_BOOK_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_BOOK_TITLE TEXT,
                $COLUMN_BOOK_PUBLISHER TEXT,
                $COLUMN_BOOK_GENRE TEXT,
                $COLUMN_BOOK_COVER_IMAGE TEXT,
                $COLUMN_USER_ID INTEGER,
                FOREIGN KEY($COLUMN_USER_ID) REFERENCES $TABLE_USER($COLUMN_USER_ID)
            );
        """.trimIndent()

        db.execSQL(createUserTable)
        db.execSQL(createBookTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BOOK")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "LibraryApp.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_USER = "user"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_USER_NAME = "name"
        const val COLUMN_USER_EMAIL = "email"
        const val COLUMN_USER_PROFILE_IMAGE = "profile_image"

        const val TABLE_BOOK = "book"
        const val COLUMN_BOOK_ID = "book_id"
        const val COLUMN_BOOK_TITLE = "title"
        const val COLUMN_BOOK_PUBLISHER = "publisher"
        const val COLUMN_BOOK_GENRE = "genre"
        const val COLUMN_BOOK_COVER_IMAGE = "cover_image"
    }
}
