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

package com.calmscient.utils.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonUtil {
    val gson = Gson()

    fun <T> toJsonString(data: T): String {
        return gson.toJson(data)
    }

    public inline fun <reified T> fromJsonString(json: String): T {
        return gson.fromJson(json, object : TypeToken<T>() {}.type)
    }
}
