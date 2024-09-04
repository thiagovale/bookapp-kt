package com.example.bookapp.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.bookapp.DbHelper
import com.example.bookapp.models.User

class UserDao(private val context: Context) {
    private val dbHelper = DbHelper(context)

    fun addUser(user: User): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DbHelper.COLUMN_USER_NAME, user.name)
            put(DbHelper.COLUMN_USER_EMAIL, user.email)
            put(DbHelper.COLUMN_USER_PROFILE_IMAGE, user.profileImage)
        }
        return db.insert(DbHelper.TABLE_USER, null, values)
    }

    fun getUserById(id: Int): User? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DbHelper.TABLE_USER, null,
            "${DbHelper.COLUMN_USER_ID} = ?", arrayOf(id.toString()),
            null, null, null
        )

        return cursorToUser(cursor)
    }

    private fun cursorToUser(cursor: Cursor): User? {
        if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_USER_ID))
            val userName = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_USER_NAME))
            val userEmail = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_USER_EMAIL))
            val userProfileImage = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_USER_PROFILE_IMAGE))
            return User(userId, userName, userEmail, userProfileImage)
        }
        return null
    }
}
