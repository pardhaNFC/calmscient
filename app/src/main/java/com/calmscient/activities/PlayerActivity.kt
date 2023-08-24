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
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.calmscient.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class PlayerActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer
    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var favoritesIcon: ImageButton
    private lateinit var playPauseIcon: ImageButton
    private lateinit var heading: TextView
    private lateinit var summary: TextView
    //private lateinit var orientationButton: ImageButton
    private var isVideoPlaying = true
    private var isFavorite = false
    private var isFullscreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        playerView = findViewById(R.id.playerView)
        favoritesIcon = findViewById(R.id.favoritesIcon)
        //playPauseIcon = findViewById(R.id.playPauseIcon)
        heading = findViewById(R.id.headingTextView)
        summary = findViewById(R.id.summaryTextView)


        val contentUri = intent.getStringExtra("mediaResourceId")
        val headingText = intent.getStringExtra("heading")
        val summaryText = intent.getStringExtra("summary")
        val videoResourceId = intent.getIntExtra("videoResourceId", 0)
        val mediaItem = if (contentUri != null) {
            MediaItem.fromUri(contentUri)
        } else {
            val videoPath = "android.resource://${packageName}/$videoResourceId"
            MediaItem.fromUri(Uri.parse(videoPath))
        }

        // Initialize
        player = SimpleExoPlayer.Builder(this).build()
        playerView.player = player
        player.setMediaItem(mediaItem)
        player.playWhenReady = true
        player.prepare()

        heading.text = headingText
        summary.text = summaryText

        // Initialize GestureDetector
        gestureDetector = GestureDetectorCompat(this, this)

        // Rest of your initialization code
        initializeBinding()
        initializeVideoControl()
    }

    private fun initializeBinding() {
        findViewById<ImageButton>(R.id.orientationBtn).setOnClickListener {
            toggleOrientation()
        }

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            onBackPressed()
        }
    }

    /*private fun toggleOrientation() {
        requestedOrientation =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            }
    }*/

    /* private fun toggleOrientation() {
         if (isFullscreen) {
             requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
             val layoutParams = playerView.layoutParams as RelativeLayout.LayoutParams
             layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
             layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
             playerView.layoutParams = layoutParams
         } else {
             requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
             val layoutParams = playerView.layoutParams as RelativeLayout.LayoutParams
             layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
             layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
             playerView.layoutParams = layoutParams
         }
         isFullscreen = !isFullscreen
     }*/
    private fun toggleOrientation() {
        if (isFullscreen) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            val layoutParams = playerView.layoutParams as RelativeLayout.LayoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            playerView.layoutParams = layoutParams
        } else {
            isFullscreen = true
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            val layoutParams = playerView.layoutParams as RelativeLayout.LayoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

            playerView.layoutParams = layoutParams
        }

        // Toggle the visibility of text views
        /* val textViewsLayout = findViewById<LinearLayout>(R.id.textViewsLayout)
         textViewsLayout.visibility = if (isFullscreen) View.GONE else View.VISIBLE
 */
        isFullscreen = !isFullscreen
    }



    private fun initializeVideoControl() {
        favoritesIcon.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                favoritesIcon.setImageResource(R.drawable.ic_favorites_icon) // Set your desired color
            } else {
                favoritesIcon.setImageResource(R.drawable.ic_favorites_red) // Reset color
            }
        }

        /* playPauseIcon.setOnClickListener {
             if (isVideoPlaying) {
                 player.pause()
                 playPauseIcon.setImageResource(R.drawable.ic_play_icon) // Change to play icon
             } else {
                 player.play()
                 playPauseIcon.setImageResource(R.drawable.ic_pause_icon) // Change to pause icon

                 Handler().postDelayed({
                     playPauseIcon.setImageResource(0)// disable back to pause icon
                 }, 2000)
             }
             isVideoPlaying = !isVideoPlaying
         }
 */
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    //  playPauseIcon.setImageResource(R.drawable.ic_play_icon)
                    isVideoPlaying = false
                }
            }
        })



    }

    // Rest of your gesture detection and other methods
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

    /* override fun onDoubleTap(p0: MotionEvent): Boolean {
        *//* val viewWidth = playerView.width
        val isRightSide = p0?.x ?: 0F > viewWidth / 2

        val currentPosition = playerView.currentPosition
        val newPosition = if (isRightSide) {
            currentPosition + 10000 // Forward by 10 seconds (10000 milliseconds)
        } else {
            currentPosition - 10000 // Backward by 10 seconds (10000 milliseconds)
        }

        val duration = playerView.duration
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
*//*
        return true

    }*/
    override fun onDoubleTap(p0: MotionEvent): Boolean {
        val viewWidth = playerView.width
        val isRightSide = p0.x > viewWidth / 2

        val currentPosition = player.currentPosition
        val newPosition = if (isRightSide) {
            currentPosition + 10000 // Forward by 10 seconds (10000 milliseconds)
        } else {
            currentPosition - 10000 // Backward by 10 seconds (10000 milliseconds)
        }

        val duration = player.duration
        val finalPosition = newPosition.coerceIn(0, duration)

        player.seekTo(finalPosition)

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