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
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.calmscient.R

class SplashScreenVideoActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_splash_video)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val videoView = findViewById<VideoView>(R.id.videoView_splash)
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.calmscienthdvertical)
        videoView.setVideoURI(videoUri)
        videoView.start()

        // You might want to add a listener to detect when the video finishes playing
        videoView.setOnCompletionListener {
        /* Transition to the next screen */
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}