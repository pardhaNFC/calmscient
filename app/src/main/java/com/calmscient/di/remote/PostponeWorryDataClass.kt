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

package com.calmscient.di.remote

data class PostponeWorryDataClass(
    val theViewType: Int,
    val text1: String?,
    val text2: String?,
    val text3: String?,
    val text4: String?,
    val anxietybulb: Int?,
    val imageanxiety: Int?,
    val text5: String?,
    val text6: String?,
    val text7: String?,
    var selectedOption: Int = -1
)
/*data class PostponeScreen1(val text1:String)
data class PostponeScreen2(val textTitle1:String)*/

