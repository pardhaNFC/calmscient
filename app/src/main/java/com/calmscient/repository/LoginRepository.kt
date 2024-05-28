package com.calmscient.repository

import com.calmscient.ApiService
import com.calmscient.di.remote.request.LoginRequest
import com.calmscient.di.remote.response.LoginResponse
import retrofit2.Call
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: ApiService)
{
    fun loginUser(loginRequest: LoginRequest): Call<LoginResponse> {
        return apiService.loginUser(loginRequest)
    }
}
