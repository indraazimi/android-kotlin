package com.indraazimi.helloworld.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Diary::class], version = 1)
abstract class DiaryDb : RoomDatabase() {

    abstract val dao: DiaryDao

    companion object {

        @Volatile
        private var INSTANCE: DiaryDb? = null

        fun getInstance(context: Context): DiaryDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DiaryDb::class.java,
                        "diary.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}