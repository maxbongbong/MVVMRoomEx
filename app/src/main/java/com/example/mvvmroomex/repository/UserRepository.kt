package com.example.mvvmroomex.repository

import androidx.lifecycle.LiveData
import com.example.mvvmroomex.db.UserDao
import com.example.mvvmroomex.db.UserDatabase
import com.example.mvvmroomex.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(mDatabase: UserDatabase) {

    private val userDao = mDatabase.userDao()
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    companion object {
        private var sInstance: UserRepository? = null
        fun getInstance(database: UserDatabase): UserRepository {
            return sInstance ?: synchronized(this) {
                val instance = UserRepository(database)
                sInstance = instance
                instance
            }
        }
    }

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): LiveData<List<User>> {
        return userDao.searchDatabase(searchQuery)
    }
}