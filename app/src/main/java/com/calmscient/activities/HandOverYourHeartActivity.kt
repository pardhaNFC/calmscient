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
import android.view.View
import android.view.WindowManager
import com.calmscient.databinding.ButterflyhugExercisesBinding
import com.calmscient.databinding.HandoverYourHeartExercisesBinding
class HandOverYourHeartActivity : AppCompat() {
    private lateinit var binding: HandoverYourHeartExercisesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HandoverYourHeartExercisesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.menuicon.setOnClickListener {
            finish()
        }
    }
}