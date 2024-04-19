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

package com.calmscient.repository

import com.calmscient.ApiService
import com.calmscient.di.remote.request.MenuItemRequest
import com.calmscient.di.remote.response.MenuItemsResponse
import retrofit2.Call
import javax.inject.Inject

class MenuItemRepository @Inject constructor(private val apiService: ApiService)
{
    fun fetchMenuItems(menuItemRequest: MenuItemRequest): Call<MenuItemsResponse> {
        return apiService.fetchMenuItems(menuItemRequest)
    }
}