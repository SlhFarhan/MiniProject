package com.farhansolih0009.miniproject.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhansolih0009.miniproject.database.UserDao
import com.farhansolih0009.miniproject.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(private val userDao: UserDao) : ViewModel() {

    fun register(user: User, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            if (userDao.getUser(user.username) != null) {
                withContext(Dispatchers.Main) {
                    onError("Username sudah digunakan!")
                }
            } else {
                userDao.insert(user)
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun login(user: User, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val foundUser = userDao.getUser(user.username)
            withContext(Dispatchers.Main) {
                if (foundUser == null) {
                    onError("Username tidak ditemukan!")
                } else if (foundUser.pass != user.pass) {
                    onError("Password salah!")
                } else {
                    onSuccess()
                }
            }
        }
    }
}