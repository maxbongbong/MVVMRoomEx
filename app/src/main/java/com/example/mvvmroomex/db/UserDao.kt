package com.example.mvvmroomex.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import com.example.mvvmroomex.model.User

@Dao
interface UserDao {

    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    suspend fun addUser(user : User)

    @Insert
    suspend fun addUserDb(users : List<User>)

    @Update
    suspend fun updateUser(user : User)

    @Delete
    suspend fun deleteUser(user : User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE name LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : LiveData<List<User>>

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

}