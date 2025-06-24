package com.farhansolih0009.miniproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.farhansolih0009.miniproject.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getUser(username: String): User?
}