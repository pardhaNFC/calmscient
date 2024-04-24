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

package com.calmscient.di.remote.response

data class ScreeningResponse(
    val statusResponse: ScreeningsStatusResponse,
    val screeningList: List<ScreeningItem>
)
data class ScreeningsStatusResponse(
    val responseMessage: String,
    val responseCode: Int
)

data class ScreeningItem(
    val startDate: String?,
    val patientID: Int,
    val score: Int,
    val completionDate: String?,
    val plid: Int,
    val screeningReminder: String,
    val screeningID: Int,
    val screeningType: String,
    val assessmentID: Int,
    val archiveFlag: Int,
    val clientID: Int,
    val screeningStatus: String
)