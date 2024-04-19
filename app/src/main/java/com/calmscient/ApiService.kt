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

package com.calmscient

import com.calmscient.di.remote.request.MenuItemRequest
import com.calmscient.di.remote.request.LoginRequest
import com.calmscient.di.remote.response.MenuItemsResponse
import com.calmscient.di.remote.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("patients/patientDetails/userLogin")
    fun loginUser(@Body requestBody: LoginRequest): Call<LoginResponse>


    @POST("identity/menu/fetchMenus")
    fun fetchMenuItems(@Body requestBody: MenuItemRequest): Call<MenuItemsResponse>
}
