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

package com.calmscient.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calmscient.utils.LocaleHelper
import com.calmscient.utils.common.SavePreferences

open class AppCompat : AppCompatActivity() {
lateinit var savePref:SavePreferences
lateinit var langHelper:LocaleHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savePref = SavePreferences(this)
        langHelper = LocaleHelper(this)
        langHelper.setLocale(this,savePref.getLanguageMode()!!)
    }
}