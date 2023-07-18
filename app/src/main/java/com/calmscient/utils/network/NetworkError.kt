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

package com.calmscient.utils.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkError (
    val status: Int = -1,
    @Expose @SerializedName("statusCode") val statusCode: String = "-1",
    @Expose @SerializedName("message") val message: String = "Something went wrong"
)