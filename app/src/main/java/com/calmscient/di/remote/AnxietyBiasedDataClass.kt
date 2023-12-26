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

data class AnxietyBiasedDataClass( val text1: String?,
                                   val imageanxiety: Int?,
                                   val text2: String?,
                                   val text3: String?,
                                   var selectedOption: Int = -1)