package com.kygoinc.jetpackcoopapp.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.kygoinc.jetpackcoopapp.Api.OkHttpHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class LoginViewModel : ViewModel() {

    private val okHttpHelper = OkHttpHelper()
    private val apiUrl = "https://dummyjson.com/auth/login"
    private val _authResult = mutableStateOf<AuthResult?>(null)

    val authResult: State<AuthResult?> = _authResult

    suspend fun authenticate(username: String, password: String) {

        if (username.isNullOrBlank() || password.isNullOrBlank()) {
            _authResult.value = AuthResult.Failure("Please fill in both fields to proceed")
        } else {
            try {


                // Create a JSON body with user credentials
                val jsonBody = """
                {
                    "username": "$username",
                    "password": "$password"
                }
            """.trimIndent()

                Log.d("loginJson", jsonBody)
                // Convert JSON string to RequestBody
                val requestBody = jsonBody.toRequestBody("application/json".toMediaType())

                // Make the POST request using OkHttpHelper
                withContext(Dispatchers.IO) {
                    val response = okHttpHelper.sendRequestWithCredentials(
                        apiUrl, username, password, "POST", requestBody
                    )

                    // Handle the response and update the authResult accordingly
                    if (response != null) {
                        when (response.code) {
                            200 -> {
                                _authResult.value = AuthResult.Success
                                Log.d("LoginVM", "Login successful")
                            }

                            401 -> {
                                _authResult.value = AuthResult.Failure("Invalid credentials")
                                Log.d("LoginVM", "Invalid credentials")
                            }

                            404 -> {
                                _authResult.value = AuthResult.Failure("Server not found")
                                Log.d("LoginVM", "Server not found")
                            }

                            else -> {
                                _authResult.value = AuthResult.Failure("Something went wrong")
                                Log.d("LoginVM", response.message)
                            }
                        }
                    }
                }


            } catch (e: Exception) {
                // Handle network or other exceptions
                _authResult.value = AuthResult.Failure("An error occurred: ${e.message}")
                Log.e("LoginVM", "An error occurred: ${e.message}", e)
            }
        }
    }

    fun resetAuthResult() {
        _authResult.value = null

    }

}

sealed class AuthResult {
    object Success : AuthResult()
    data class Failure(val errorMessage: String) : AuthResult()
}