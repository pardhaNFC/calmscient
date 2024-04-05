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

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.LayoutLoginBinding
import com.calmscient.fragments.DiscoveryFragment
import com.calmscient.fragments.UserMoodFragment
import com.calmscient.utils.ContextUtils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Locale


class LoginActivity : AppCompatActivity() {
    lateinit var binding: LayoutLoginBinding
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.btnLogin.setOnClickListener {
            navigateToDayScreen()
        }
        binding.forgotPass.setOnClickListener{
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        binding.userName.setOnFocusChangeListener(OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.userName.hint = "xxxxxxxxxxxx@gmail.com"
            }
            if (!hasFocus) {
                binding.userName.setHint(R.string.username)
            }
        })
        binding.editPassword.setOnFocusChangeListener(OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.editPassword.hint = "**********"
            }
            if (!hasFocus) {
                binding.editPassword.setHint(R.string.password)
            }
        })
        binding.termsCheckBox.setOnClickListener {
            // Open a web page with the terms and conditions when clicked
            /*val termsUrl = "https://"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(termsUrl))
            startActivity(intent)*/
        }
        binding.termsCheckBox.setOnCheckedChangeListener { _, isChecked ->
            // Enable/disable further actions based on checkbox state
            if (isChecked) {
                //navigateToDayScreen()
                // Checkbox is checked, perform actions here
                binding.termsTextView.visibility = View.GONE
            } else {
                binding.termsTextView.visibility = View.VISIBLE
                // Checkbox is unchecked, handle this case if needed
            }
        }
        binding.parentLayout.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Hide the soft keyboard
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                // Clear focus from the TextInputEditText
                binding.userNameTextInputLayout.clearFocus()
                binding.TinPassword.clearFocus()
            }
            false
        }
        val passwordToggle = findViewById<TextInputLayout>(R.id.Tin_password)
        val passwordEditText = findViewById<TextInputEditText>(R.id.edit_password)
        passwordToggle.passwordVisibilityToggleDrawable = ContextCompat.getDrawable(this, R.drawable.ic_eye_close)
        passwordToggle.setEndIconOnClickListener {
            if (passwordEditText.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                // Hide the password
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                passwordToggle.passwordVisibilityToggleDrawable = ContextCompat.getDrawable(this, R.drawable.ic_eye_close)
            } else {
                // Show the password
                passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passwordToggle.passwordVisibilityToggleDrawable = ContextCompat.getDrawable(this, R.drawable.ic_eye_open)
            }
        }
    }

    override fun onBackPressed() {
        finishAffinity();
        finish()
    }
    private fun navigateToDayScreen() {
        startActivity(Intent(this, UserMoodActivity::class.java))
    }
}