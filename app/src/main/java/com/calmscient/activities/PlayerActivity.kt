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
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.calmscient.R
import com.calmscient.utils.common.SavePreferences
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class PlayerActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer
    private lateinit var favoritesIcon: ImageButton
    private lateinit var heading: TextView
    private lateinit var summary: TextView
    private var isVideoPlaying = true
    private var isFavorite = false
    private val isPortrait = false
    lateinit var title: TextView
    lateinit var dialogText: String
    lateinit var dialog_img: ImageView
    lateinit var savePrefData: SavePreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        savePrefData = SavePreferences(this)
        playerView = findViewById(R.id.playerViewLayout)
        favoritesIcon = findViewById(R.id.favoritesIcon)
        heading = findViewById(R.id.headingTextView)
        summary = findViewById(R.id.summaryTextView)
        title = findViewById(R.id.tv_title_player)
        dialog_img = findViewById<ImageView>(R.id.informationIcon)
        dialogText = intent.getStringExtra("dialogText").toString()
        if (savedInstanceState == null) {
            // Initialize ExoPlayer only if activity is initially created

            player = SimpleExoPlayer.Builder(this).build()

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

            // Setup ExoPlayer
            playerView.player = player
            player.setMediaItem(mediaItem)
            player.playWhenReady = true
            // player.prepare()
            if (savePrefData.getAslLanguageState() == true) {
                heading.visibility = View.GONE
            } else {
                heading.visibility = View.VISIBLE
            }
            heading.text = headingText
            summary.text = summaryText
            title.text = headingText
            /*if(headingText == getString(R.string.make_plan_card6_text2)){
                summary.text = getString(R.string.video_calming_skills)
            }*/
        }

        if (dialogText.equals("null")) {
            dialog_img.visibility = View.GONE
        } else {
            dialog_img.visibility = View.VISIBLE
        }
        dialog_img.setOnClickListener {
            showInformationDialog()
        }
        // Rest of your initialization code
        initializeBinding()
        initializeVideoControl()
    }

    @SuppressLint("MissingInflatedId")
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        val isPortrait = newConfig.orientation == Configuration.ORIENTATION_PORTRAIT

        // Change the layout based on the new orientation
        if (isPortrait) {
            setContentView(R.layout.activity_player)
            // Re-bind the views to the updated portrait layout
            playerView = findViewById(R.id.playerViewLayout)
            favoritesIcon = findViewById(R.id.favoritesIcon)
            heading = findViewById(R.id.headingTextView)
            summary = findViewById(R.id.summaryTextView)
            title = findViewById(R.id.tv_title_player)

            val headingText = intent.getStringExtra("heading")
            val summaryText = intent.getStringExtra("summary")
            playerView.player = player

            if (dialogText.equals("null")) {
                dialog_img.visibility = View.GONE
            } else {
                dialog_img.visibility = View.VISIBLE
            }
            if (savePrefData.getAslLanguageState() == true) {
                heading.visibility = View.GONE
            } else {
                heading.visibility = View.VISIBLE
            }
            heading.text = headingText
            summary.text = summaryText
            title.text = headingText
            // Initialize your binding and control setup after re-binding views
            initializeBinding()
            initializeVideoControl()
        } else {
            // Load the landscape layout (activity_player_landscape.xml)
            setContentView(R.layout.activity_player_landscape)
            // Re-bind the landscape-only views (e.g., playerView and favoritesIcon)
            playerView = findViewById(R.id.playerViewLayout)
            favoritesIcon = findViewById(R.id.favoritesIcon)
            playerView.player = player

            // Initialize your landscape layout-specific control setup here
            initializeBinding()
            initializeVideoControl()
        }
    }

    override fun onPause() {
        super.onPause()
        playerView.player!!.playWhenReady = false;

    }

    override fun onStop() {
        super.onStop()
        playerView.player!!.release()
    }

    override fun onResume() {
        super.onResume()
        playerView.player!!.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        playerView.player!!.playbackState
    }

    private fun initializeBinding() {
        findViewById<ImageButton>(R.id.orientationBtn).setOnClickListener {
            toggleOrientation()
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Set the click listener for informationIcon in portrait mode only
            val informationIcon = findViewById<ImageView>(R.id.informationIcon)
            informationIcon.setOnClickListener {
                showInformationDialog()
            }

            findViewById<ImageView>(R.id.ic_glossary).setOnClickListener {
                startActivity(Intent(this, GlossaryActivity::class.java))

            }

            findViewById<ImageView>(R.id.menu_icon).setOnClickListener {
                onBackPressed()
            }
        }
        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            onBackPressed()
        }
    }

    private fun toggleOrientation() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            setContentView(R.layout.activity_player_landscape) // Load landscape layout
        } else {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            setContentView(R.layout.activity_player) // Load portrait layout
        }

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

        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    //  playPauseIcon.setImageResource(R.drawable.ic_play_icon)
                    isVideoPlaying = false
                }
            }
        })
    }

    private fun showInformationDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.information_dialog, null)
        val infoTextView = dialogView.findViewById<TextView>(R.id.dialogInfoTextView)
        val closeButton = dialogView.findViewById<ImageView>(R.id.closeDialogButton)
        val titleTextView = dialogView.findViewById<TextView>(R.id.titleVideoDialog)
        // Retrieve the dialogText from intent extras


        // Set the content of the dialog using dialogText
        infoTextView.text = dialogText
        titleTextView.text = getString(R.string.information)
        val dialogBuilder = AlertDialog.Builder(this, R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Handle the close button click
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


}