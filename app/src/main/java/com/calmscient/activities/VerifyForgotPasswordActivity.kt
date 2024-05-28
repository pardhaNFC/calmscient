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
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.calmscient.R
import com.calmscient.databinding.LayoutVerifyForgotPasswordBinding
import com.calmscient.utils.OneDigitTextWatcher


class VerifyForgotPasswordActivity : AppCompatActivity() {
    lateinit var binding: LayoutVerifyForgotPasswordBinding
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutVerifyForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.imgBack.setOnClickListener {
            navigateToLoginActivity()
        }

        binding.btnVerifyPassword.setOnClickListener{
            startActivity(Intent(this, SetNewPasswordActivity::class.java))
            finish()
        }

        // Initialize EditText views
        editText1 = binding.editText1
        editText2 = binding.editText2
        editText3 = binding.editText3
        editText4 = binding.editText4

        // Set up OneDigitTextWatcher for all EditText fields
        setUpOneDigitTextWatcher()
    }

    private fun setUpOneDigitTextWatcher() {
        editText1.addTextChangedListener(OneDigitTextWatcher(editText1, editText2))
        editText2.addTextChangedListener(OneDigitTextWatcher(editText2, editText3))
        editText3.addTextChangedListener(OneDigitTextWatcher(editText3, editText4))
        editText4.addTextChangedListener(OneDigitTextWatcher(editText4, editText4))
        // The last EditText field does not have a next EditText to move focus to
    }


    override fun onBackPressed() {
        navigateToLoginActivity()
    }

    private fun navigateToLoginActivity() {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
        finish()
    }
}

