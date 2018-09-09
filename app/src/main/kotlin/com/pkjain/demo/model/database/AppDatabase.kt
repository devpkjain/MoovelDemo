package com.pkjain.demo.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.pkjain.demo.model.Post
import com.pkjain.demo.model.PostDao

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}