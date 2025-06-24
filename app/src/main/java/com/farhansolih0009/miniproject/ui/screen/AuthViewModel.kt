package com.farhansolih0009.miniproject.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhansolih0009.miniproject.R
import com.farhansolih0009.miniproject.database.UserDao
import com.farhansolih0009.miniproject.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(private val userDao: UserDao) : ViewModel() {

    fun register(user: User, onSuccess: () -> Unit, onError: (Int) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            if (userDao.getUser(user.username) != null) {
                withContext(Dispatchers.Main) {
                    onError(R.string.error_username_taken)
                }
            } else {
                userDao.insert(user)
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun login(user: User, onSuccess: () -> Unit, onError: (Int) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val foundUser = userDao.getUser(user.username)
            withContext(Dispatchers.Main) {
                if (foundUser == null) {
                    onError(R.string.error_user_not_found)
                } else if (foundUser.pass != user.pass) {
                    onError(R.string.error_wrong_password)
                } else {
                    onSuccess()
                }
            }
        }
    }
}