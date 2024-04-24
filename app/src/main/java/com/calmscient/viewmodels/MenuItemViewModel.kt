package com.calmscient.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calmscient.di.remote.request.MenuItemRequest
import com.calmscient.di.remote.response.MenuItem
import com.calmscient.di.remote.response.MenuItemsResponse
import com.calmscient.repository.MenuItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject


class MenuItemViewModel @Inject constructor(private val menuItemRepository: MenuItemRepository) : ViewModel() {
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val resultLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val menuItemsLiveData: MutableLiveData<List<MenuItem>> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun fetchMenuItems(plid: Int, parentId: Int, patientId: Int, clientId: Int) {
        loadingLiveData.value = true // Show loader
        viewModelScope.launch {
            try {
                val newUrl = "http://20.197.5.97:8083/identity/menu/fetchMenus"
                menuItemRepository.setBaseUrl(newUrl)
                Log.d("URL", "Request URL: $newUrl")

                val request = MenuItemRequest(plid, parentId, patientId, clientId)

                Log.d("MenuItemViewModel", "Request: $request")
                val response = menuItemRepository.fetchMenuItems(request)
                Log.d("MenuItemViewModel", "Response: $response")
                handleResponse(response)
            } catch (e: Exception) {
                e.printStackTrace()
                errorLiveData.value = "Failed to fetch menu items."
            } finally {
                loadingLiveData.value = false
            }
        }
    }

    private suspend fun handleResponse(call: Call<MenuItemsResponse>) {
        withContext(Dispatchers.IO) {
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    val isSuccess = response.body()?.statusResponse?.responseCode == 200
                    val menuItemsResponse = response.body()
                    if (menuItemsResponse != null && isSuccess) {
                        Log.d("MenuItemViewModel", "Response: $menuItemsResponse")
                        menuItemsLiveData.postValue(menuItemsResponse.menuItems)
                    } else {
                        errorLiveData.postValue("Empty response")
                    }
                    resultLiveData.postValue(isSuccess)
                } else {
                    errorLiveData.postValue("Error: ${response.code()}")
                    resultLiveData.postValue(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errorLiveData.postValue("Exception: ${e.message}")
                resultLiveData.postValue(false)
            }
        }
    }
}
