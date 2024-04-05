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
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.calmscient.R
import com.calmscient.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.imgBack.setOnClickListener {
            navigateToLoginActivity()
        }
        binding.emailOrPhoneNumber.setOnFocusChangeListener(View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.emailOrPhoneNumber.hint = "xxxxxxxxxxxx@gmail.com"
            }
            if (!hasFocus) {
                binding.emailOrPhoneNumber.setHint(R.string.your_email_phone_number)
            }
        })

        binding.btnResetPassword.setOnClickListener{
            startActivity(Intent(this, VerifyForgotPasswordActivity::class.java))
            finish()
        }
    }


    override fun onBackPressed() {
        navigateToLoginActivity()
    }
    private fun navigateToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}