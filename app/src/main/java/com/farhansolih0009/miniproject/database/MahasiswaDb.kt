package com.farhansolih0009.miniproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.farhansolih0009.miniproject.model.Mahasiswa
import com.farhansolih0009.miniproject.model.User

// In MahasiswaDb.kt

@Database(entities = [Mahasiswa::class, User::class], version = 2, exportSchema = false) // Naikkan versi ke 2
abstract class MahasiswaDb : RoomDatabase() {

    abstract val dao: MahasiswaDao
    abstract val userDao: UserDao // Tambahkan ini

    companion object {
        @Volatile
        private var INSTANCE: MahasiswaDb? = null

        fun getInstance(context: Context): MahasiswaDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MahasiswaDb::class.java,
                        "mahasiswa.db"
                    )
                        // Tambahkan fallback untuk migrasi
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}