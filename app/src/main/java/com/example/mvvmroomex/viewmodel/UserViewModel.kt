package com.example.mvvmroomex.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.mvvmroomex.db.UserDatabase
import com.example.mvvmroomex.model.User
import com.example.mvvmroomex.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.mvvmroomex.base.BaseViewModel

class UserViewModel(application: Application) : BaseViewModel(application) {

    private val repository: UserRepository =
        UserRepository(UserDatabase.getDatabase(application, viewModelScope))

    val readAllUser: LiveData<List<User>> = repository.readAllData

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

//    fun searchDatabase(searchQuery: String): LiveData<List<User>> {
//        return repository.searchDatabase(searchQuery)
//    }

//    class Factory(private val application: Application) : ViewModelProvider.Factory{
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return UserViewModel(application) as T
//        }
//    }
}