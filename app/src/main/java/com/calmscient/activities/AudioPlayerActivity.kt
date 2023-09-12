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

import android.app.ProgressDialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.calmscient.R
import com.calmscient.adapters.TakingControlSummaryCardAdapter
import com.calmscient.databinding.ActivityAudioPlayerBinding
import com.calmscient.databinding.ActivityMakeAplanBinding
import com.google.android.exoplayer2.MediaItem

class AudioPlayerActivity : AppCompat(), MediaPlayer.OnPreparedListener,
    MediaPlayer.OnBufferingUpdateListener {
    private lateinit var binding: ActivityAudioPlayerBinding

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var waveformView: WaveformView
    private lateinit var playButton: ImageView
    private lateinit var handler: Handler
    private lateinit var title: TextView
    private var audioFilePath: String? = null
    private var isMediaPlayerInitialized = false

//    lateinit var progressDialog: CustomProgressDialog

    private lateinit var loadingDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // audioFilePath  = intent.getStringExtra("audioResourceId")
        val description = intent.getStringExtra("description")

        //  mediaPlayer = MediaPlayer.create(this, R.raw.audio1)
        if (isNetworkConnected(this)) {
            mediaPlayer = MediaPlayer()
            binding.tvTitle.text = description
            if(description == getString(R.string.meet_nora_austin)){
                binding.audioScrollView.visibility = View.VISIBLE
            }
//        mediaPlayer = MediaPlayer.create(this, Uri.parse(audioFilePath))
            waveformView = findViewById(R.id.waveformView)
            playButton = findViewById(R.id.playButton)

            //progressDialog = CustomProgressDialog(this)
            loadingDialog = ProgressDialog(this)
            loadingDialog.setMessage("Loading Audio...")
            loadingDialog.setCanceledOnTouchOutside(false)
            binding.audioProgressBar.indeterminateDrawable =
                resources.getDrawable(R.drawable.circular_progressbar)
            audioFilePath = intent.getStringExtra("audioResourceId")
            handler = Handler()
            mediaPlayer.setOnPreparedListener(this)
            mediaPlayer.setOnBufferingUpdateListener(this)

            // seekBar.max = mediaPlayer.duration
            binding.playButton.setOnClickListener {
                /*  if (!isMediaPlayerInitialized) {
                      loadingDialog.setCanceledOnTouchOutside(false)
                      loadingDialog.show()
                  } */
                binding.audioProgressBar.visibility = View.VISIBLE
                if (!isMediaPlayerInitialized) {
                    /*loadingDialog.setCanceledOnTouchOutside(false)
                    loadingDialog.show()*/
                    // Initialize the mediaPlayer if it hasn't been initialized yet
                    mediaPlayer.setDataSource(audioFilePath)
                    mediaPlayer.prepareAsync()

                    mediaPlayer.setOnPreparedListener { mp ->
                        mp.start()
                        // Dismiss the buffering dialog when the media player is prepared

                        //loadingDialog.dismiss()
                        playButton.setImageResource(R.drawable.ic_audio_pause)
                        waveformView.visibility = View.VISIBLE
                        updateWaveformView()
                    }
                    mediaPlayer.setOnCompletionListener {
                        // Playback completed, reset the play button icon
                        playButton.setImageResource(R.drawable.ic_audio_play)
                    }
                    isMediaPlayerInitialized = true
                } else {
                    if (mediaPlayer.isPlaying) {
                        binding.audioProgressBar.visibility = View.GONE
                        mediaPlayer.pause()
                        playButton.setImageResource(R.drawable.ic_audio_play)
                    } else {
                        mediaPlayer.start()
                        playButton.setImageResource(R.drawable.ic_audio_pause)
                        waveformView.visibility = View.VISIBLE
                        updateWaveformView()
                        // No need to show the dialog here since it was already shown when the play button was clicked.
                    }
                }
            }
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        }
            binding.audioBackward.setOnClickListener {
                skipBackward()
            }

            binding.audioForward.setOnClickListener {
                skipForward()
            }

            binding.menuIcon.setOnClickListener {
                onBackPressed()
            }

            binding.icGlossary.setOnClickListener {
                // isMediaPlayerInitialized = false
                startActivity(Intent(this, GlossaryActivity::class.java))
            }

            binding.informationIcon.setOnClickListener {
                showInformationDialog()
            }

    }

    private fun updateWaveformView() {
        val audioDuration = mediaPlayer.duration
        val updateInterval = 100 // Update more frequently for smoother progression
        binding.audioProgressBar.visibility = View.GONE
        handler.post(object : Runnable {
            override fun run() {
                val currentPosition = mediaPlayer.currentPosition
                val progress = currentPosition.toFloat() / audioDuration.toFloat()

                // Set the waveform progress
                waveformView.setWaveformProgress(progress)

                // Check if playback has completed
                if (currentPosition < audioDuration) {
                    handler.postDelayed(this, updateInterval.toLong())
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun skipForward() {
        val currentPosition = mediaPlayer.currentPosition
        val newPosition = currentPosition + 10000 // Skip forward by 10 seconds (in milliseconds)
        if (newPosition <= mediaPlayer.duration) {
            mediaPlayer.seekTo(newPosition)
        }
    }

    private fun skipBackward() {
        val currentPosition = mediaPlayer.currentPosition
        val newPosition = currentPosition - 10000 // Skip backward by 10 seconds (in milliseconds)
        if (newPosition >= 0) {
            mediaPlayer.seekTo(newPosition)
        }
    }

    override fun onPause() {
        super.onPause()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    private fun showInformationDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.audio_information_dialog, null)
        //  val infoTextView = dialogView.findViewById<TextView>(R.id.dialogTextView)
        val closeButton = dialogView.findViewById<ImageView>(R.id.closeButton)
        val titleTextView = dialogView.findViewById<TextView>(R.id.titleTextView)

        /*  // Retrieve the dialogText from intent extras
          val dialogText = intent.getStringExtra("dialogText")

          // Set the content of the dialog using dialogText
          infoTextView.text = dialogText*/
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

    override fun onPrepared(mp: MediaPlayer?) {
        // Media player is prepared, start playback
        mediaPlayer.start()
    }

    override fun onBufferingUpdate(mp: MediaPlayer?, percent: Int) {
        // Show the custom loader when buffering is in progress
        if (percent < 100) {
            //binding.audioProgressBar.visibility = View.VISIBLE
        } else {
            // Hide the custom loader when buffering is complete
            //binding.audioProgressBar.visibility = View.GONE
        }
    }

}