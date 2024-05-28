/*
 *
 *      Copyright (c) 2023- NFC Solutions, - All Rights Reserved
 *      All source code contained herein remains the property of NFC Solutions Incorporated
 *      and protected by trade secret or copyright law of USA.
 *      Dissemination, De-compilation, Modification and Distribution are strictly prohibited unless
 *      there is a prior written permission or license agreement from NFC Solutions.
 *
 *      Author : @Pardha Saradhi
 */

package com.calmscient.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calmscient.ApiService
import com.calmscient.di.remote.request.ScreeningRequest
import com.calmscient.di.remote.response.LoginResponse
import com.calmscient.di.remote.response.ScreeningItem
import com.calmscient.di.remote.response.ScreeningResponse
import com.calmscient.repository.ScreeningsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class ScreeningViewModel @Inject constructor(private val screeningRepository: ScreeningsRepository) : ViewModel() {
    val screeningListLiveData: MutableLiveData<List<ScreeningItem>> = MutableLiveData()
    val screeningsResultLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val failureLiveData: MutableLiveData<String> = MutableLiveData()
    val failureResponseData: MutableLiveData<ScreeningResponse?> = MutableLiveData()

    // Empty constructor
    constructor(apiService: ApiService) : this(ScreeningsRepository(apiService)) {
        // You can initialize any additional dependencies here if needed
    }

    fun getScreeningList(patientId: Int, clientId: Int, patientLocationId: Int) {
        loadingLiveData.value = true // Show loader
        viewModelScope.launch {
            try {
                val request = ScreeningRequest(patientId, clientId, patientLocationId)
                val response = screeningRepository.fetchScreeningsMenuItems(request)
                handleResponse(response)
            } catch (e: Exception) {
                e.printStackTrace()
                failureLiveData.value = "Failed to fetch screening list."
            } finally {
                loadingLiveData.value = false
            }
        }
    }

    private suspend fun handleResponse(call: Call<ScreeningResponse>) {
        withContext(Dispatchers.IO) {
            try {
                val response = call.execute()

                if (response.isSuccessful) {
                    val isSuccess = response.body()?.statusResponse?.responseCode == 200
                    val screeningItems = response.body()
                    if(isSuccess && screeningItems != null) {
                        screeningListLiveData.postValue(screeningItems.screeningList)
                        screeningsResultLiveData.postValue(isSuccess)
                        Log.d("ScreeningsViewModel", "Response Data: ${response.body()}")
                    }
                    else {
                        failureResponseData.postValue(response.body())
                    }
                    screeningsResultLiveData.postValue(false)
                } else {
                    failureLiveData.postValue("Failed to fetch screening list.")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                failureLiveData.postValue("Failed to fetch screening list.")
                screeningsResultLiveData.postValue(false)
            }
        }
    }
}
