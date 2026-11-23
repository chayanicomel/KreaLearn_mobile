package com.example.krealearn.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.krealearn.data.dao.KelasKreatorDao
import com.example.krealearn.data.entity.KelasKreatorEntity

@Database(
    entities = [KelasKreatorEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KreaDatabase : RoomDatabase() {
    abstract fun kelasDao(): KelasKreatorDao

    companion object {
        @Volatile
        private var INSTANCE: KreaDatabase? = null

        fun getInstance(context: Context): KreaDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    KreaDatabase::class.java,
                    "krealearn_database"
                ).build().also { INSTANCE = it }
            }
    }
}