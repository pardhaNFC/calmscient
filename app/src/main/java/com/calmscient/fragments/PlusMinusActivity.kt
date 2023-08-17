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

package com.calmscient.fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.calmscient.R

class PlusMinusActivity: AppCompatActivity() {

    private var counter = 0
    private lateinit var counterButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_sample)

        //counterButton = findViewById(R.id.counterButton)
        updateCounterText()

        findViewById<Button>(R.id.minusButton).setOnClickListener {
            decrementCounter()
        }

        findViewById<Button>(R.id.plusButton).setOnClickListener {
            incrementCounter()
        }
    }

    private fun decrementCounter() {
        counter--
        updateCounterText()
    }

    private fun incrementCounter() {
        counter++
        updateCounterText()
    }

    private fun updateCounterText() {
        counterButton.text = counter.toString()
    }
}