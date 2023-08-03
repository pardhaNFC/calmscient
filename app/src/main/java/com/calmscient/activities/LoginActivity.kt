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
import android.view.View.OnFocusChangeListener
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.LayoutLoginBinding
import com.calmscient.fragments.UserMoodFragment


class LoginActivity :  AppCompatActivity() {
lateinit var binding: LayoutLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.btnLogin.setOnClickListener{
            navigateToDayScreen()
        }
        binding.userName.setOnFocusChangeListener(OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.userName.hint = "xxxxxxxxxxxx@gmail.com"
            }
            if(!hasFocus){
                binding.userName.setHint(R.string.username)
            }
        })
        binding.editPassword.setOnFocusChangeListener(OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.editPassword.hint = "**********"
            }
            if(!hasFocus){
                binding.editPassword.setHint(R.string.password)
            }

        })
    }

    private fun navigateToDayScreen() {
        startActivity(Intent(this, UserMoodActivity::class.java))
    }

}