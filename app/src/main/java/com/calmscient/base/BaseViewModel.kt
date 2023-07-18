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

package com.calmscient.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calmscient.R
import com.calmscient.utils.common.Resource
import com.calmscient.utils.network.NetworkHelper
import javax.net.ssl.HttpsURLConnection

abstract class BaseViewModel(private val networkHelper: NetworkHelper):ViewModel() {
    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()
    val logOut: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    protected fun responseFailureError(error: String) {
        messageString.postValue(Resource.error(error))
    }

    protected fun handleNetworkError(err: Throwable?) = err?.let {
        networkHelper.castToNetworkError(it).run {
            when (status) {
                1 -> messageStringId.postValue(Resource.error(R.string.network_default_error))
                0 -> messageStringId.postValue(Resource.error(R.string.server_connection_error))
                HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                    forcedLogoutUser()
//                        messageStringId.postValue(Resource.error(R.string.server_connection_error))
                }
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> messageStringId.postValue(Resource.error(R.string.network_internal_error))
                HttpsURLConnection.HTTP_UNAVAILABLE -> messageStringId.postValue(Resource.error(R.string.network_server_not_available))
                HttpsURLConnection.HTTP_MOVED_TEMP -> messageStringId.postValue(Resource.error(R.string.network_invalidation_error))
                else -> messageString.postValue(Resource.error(message))
            }
        }
    }

    protected open fun forcedLogoutUser() {
        // do something
        logOut.postValue(Resource.success(true))
    }
    abstract fun onCreate()
}