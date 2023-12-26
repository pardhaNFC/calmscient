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

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.calmscient.R

class CustomProgressDialog(context: Context) : Dialog(context) {
    private lateinit var progressBar: ProgressBar
    private lateinit var messageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_progress_dialog)

        progressBar = findViewById(R.id.customProgressBar)
        messageTextView = findViewById(R.id.customMessage)
    }

    fun simulateTimeConsumingTask() {
        // Simulate some time-consuming task
        Thread.sleep(3000) // For example, 3 seconds
    }

    fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoader() {
        progressBar.visibility = View.GONE
    }
}