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

package com.calmscient.data.remote

data class JournalEntryDataClass(
    val date: String, // Date in the format "MM/dd/yyyy"
    var description: String,
    val cardCount: Int, // Number of cards to display for this date
    var isExpanded: Boolean = false
)
