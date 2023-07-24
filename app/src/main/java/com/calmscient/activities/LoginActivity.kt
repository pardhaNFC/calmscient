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

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.calmscient.R
import com.calmscient.databinding.LayoutLoginBinding
import java.util.Calendar
import java.util.Date


class LoginActivity :  ComponentActivity() {
lateinit var binding: LayoutLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnLogin.setOnClickListener{
            navigateToDayScreen()
        }
    }

    private fun navigateToDayScreen() {
        startActivity(Intent(this, UserMoodActivity::class.java))
    }

}