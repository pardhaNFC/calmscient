package com.calmscient.viewmodels
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calmscient.ApiService
import com.calmscient.di.remote.request.LoginRequest
import com.calmscient.di.remote.response.LoginResponse
import com.calmscient.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {
    val loginResultLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val responseData: MutableLiveData<LoginResponse?> = MutableLiveData()
    val failureResponseData: MutableLiveData<LoginResponse?> = MutableLiveData()
    val loginResponse: MutableLiveData<LoginResponse?>
        get() = responseData

    // Empty constructor
    constructor(apiService: ApiService) : this(LoginRepository(apiService)) {
        // You can initialize any additional dependencies here if needed
    }

    fun loginUser(username: String, password: String) {
        loadingLiveData.value = true // Show loader
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(username, password)
                val response = loginRepository.loginUser(loginRequest)
                handleResponse(response)
            } catch (e: Exception) {
                e.printStackTrace()
                loginResultLiveData.value = false
            } finally {
                loadingLiveData.value = false
            }
        }
    }
    fun setResponseDate(response : LoginResponse)
    {
        responseData.value = response
        Log.d("LoginViewModel Class","$responseData")
    }

    private suspend fun handleResponse(call: Call<LoginResponse>) {
        withContext(Dispatchers.IO) {
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    val isValidLogin = response.body()?.statusResponse?.responseCode == 200
                    if (isValidLogin) {
                        responseData.postValue(response.body()) // Store response data
                        responseData.value?.let { setResponseDate(it) }
                    } else {
                        failureResponseData.postValue(response.body())
                    }
                    loginResultLiveData.postValue(isValidLogin)
                } else {
                    loginResultLiveData.postValue(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                loginResultLiveData.postValue(false)
            }
        }
    }
}

