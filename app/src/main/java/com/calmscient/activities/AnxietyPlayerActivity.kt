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
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.GestureDetector
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.calmscient.R
class AnxietyPlayerActivity: AppCompatActivity(),
    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private lateinit var videoView: VideoView
    private lateinit var mediaController: MediaController
    private lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        videoView = findViewById(R.id.playerVideoView)

        // Retrieve the content Uri from the intent extras
        /*val contentUri = intent.getParcelableExtra<Uri>("contentUri")
        videoView.setVideoURI(contentUri)*/

        val videoUrl = intent.getStringExtra("mediaResourceId")
        videoView.setVideoURI(Uri.parse(videoUrl))

        // Initialize media controller
        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Initialize GestureDetector
        gestureDetector = GestureDetectorCompat(this, this)

        // Start video playback
        videoView.start()
        initializeBinding()
    }
    private fun initializeBinding() {
        findViewById<ImageButton>(R.id.orientationBtn).setOnClickListener {
            toggleOrientation()
        }

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            onBackPressed()
        }
    }

    private fun toggleOrientation() {
        requestedOrientation = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }

    override fun onDown(p0: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
    }

    override fun onFling(
        p0: MotionEvent,
        p1: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }

    override fun onDoubleTap(p0: MotionEvent): Boolean {
        val viewWidth = videoView.width
        val isRightSide = p0?.x ?: 0F > viewWidth / 2

        val currentPosition = videoView.currentPosition
        val newPosition = if (isRightSide) {
            currentPosition + 10000 // Forward by 10 seconds (10000 milliseconds)
        } else {
            currentPosition - 10000 // Backward by 10 seconds (10000 milliseconds)
        }

        val duration = videoView.duration
        val finalPosition = newPosition.coerceIn(0, duration)

        videoView.seekTo(finalPosition)

        // Show overlay message
        val inflater = LayoutInflater.from(this)
        val overlayLayout = inflater.inflate(R.layout.layout_overlay_message, null)
        val overlayMessage = overlayLayout.findViewById<TextView>(R.id.overlayMessage)
        overlayMessage.text = if (isRightSide) {
            "+10 seconds"
        } else {
            "-10 seconds"
        }

        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        if (isRightSide) {
            layoutParams.gravity = Gravity.CENTER or Gravity.END
        } else {
            layoutParams.gravity = Gravity.CENTER or Gravity.START
        }
        overlayLayout.layoutParams = layoutParams


        // Add the overlay layout to the root layout
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        rootView.addView(overlayLayout)

        // Remove the overlay layout after a delay
        Handler().postDelayed({
            rootView.removeView(overlayLayout)
        }, 2000) // Adjust the delay as needed

        return true

    }

    override fun onScroll(
        p0: MotionEvent,
        p1: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        // Implement the logic for handling scroll gestures here if needed
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        // Implement the logic for handling double tap events here if needed
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        // Implement the logic for handling confirmed single tap events here if needed
        return true
    }
}