package com.farhansolih0009.miniproject.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.farhansolih0009.miniproject.R
import com.farhansolih0009.miniproject.database.MahasiswaDb
import com.farhansolih0009.miniproject.model.User
import com.farhansolih0009.miniproject.navigation.Screen
import com.farhansolih0009.miniproject.util.SettingsDataStore
import com.farhansolih0009.miniproject.util.ViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val db = MahasiswaDb.getInstance(context)
    val factory = ViewModelFactory(db.dao, db.userDao)
    val viewModel: AuthViewModel = viewModel(factory = factory)
    val dataStore = SettingsDataStore(context)
    val scope = rememberCoroutineScope()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.login_title), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(id = R.string.username_label)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.password_label)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.login(User(username = username, pass = password),
                        onSuccess = {
                            scope.launch {
                                dataStore.saveLoginStatus(username)
                                navController.navigate(Screen.LoginSuccessSplash.route) {
                                    popUpTo(Screen.Login.route) { inclusive = true }
                                }
                            }
                        },
                        onError = { errorMsg ->
                            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                        }
                    )
                } else {
                    val errorMessage = context.getString(R.string.error_empty_data)
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.login_button))
        }
        TextButton(onClick = { navController.navigate(Screen.Register.route) }) {
            Text(stringResource(id = R.string.register_prompt))
        }
    }
}