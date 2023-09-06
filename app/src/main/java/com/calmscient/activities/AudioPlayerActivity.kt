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
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.calmscient.R
import com.calmscient.databinding.ActivityAudioPlayerBinding
import com.calmscient.databinding.ActivityMakeAplanBinding
import com.google.android.exoplayer2.MediaItem

class AudioPlayerActivity : AppCompat() {

    private lateinit var binding: ActivityAudioPlayerBinding

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var waveformView: WaveformView
    private lateinit var playButton: ImageView
    private lateinit var handler: Handler
    private lateinit var title : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val audioFilePath  = intent.getStringExtra("audioResourceId")
        val description = intent.getStringExtra("description")

        //  mediaPlayer = MediaPlayer.create(this, R.raw.audio1)

        binding.tvTitle.text = description

        mediaPlayer = MediaPlayer.create(this, Uri.parse(audioFilePath))

        seekBar = findViewById(R.id.seekBar)
        waveformView = findViewById(R.id.waveformView)
        playButton = findViewById(R.id.playButton)
        handler = Handler()

        seekBar.max = mediaPlayer.duration

        binding.playButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                playButton.setImageResource(R.drawable.ic_audio_play)
            } else {
                mediaPlayer.start()
                playButton.setImageResource(R.drawable.ic_audio_pause)
                updateSeekBar()
                updateWaveformView()
            }
        }

        binding.audioBackward.setOnClickListener{
            skipBackward()
        }

        binding.audioForward.setOnClickListener{
            skipForward()
        }

        binding.menuIcon.setOnClickListener {
            onBackPressed()
        }

        binding.icGlossary.setOnClickListener {
/*
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }*/
            //mediaPlayer.stop()
            startActivity(Intent(this,GlossaryActivity::class.java))
        }

        binding.menuIcon.setOnClickListener {
            onBackPressed()
        }

        binding.informationIcon.setOnClickListener{

            showInformationDialog()
        }
    }

    private fun updateSeekBar() {
        seekBar.progress = mediaPlayer.currentPosition
        handler.postDelayed({ updateSeekBar() }, 1000) // Update every second
    }

    private fun updateWaveformView() {
        val audioDuration = mediaPlayer.duration
        val updateInterval = 100 // Update more frequently for smoother progression

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

    private fun showInformationDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.audio_information_dialog, null)
        //  val infoTextView = dialogView.findViewById<TextView>(R.id.dialogTextView)
        val closeButton = dialogView.findViewById<ImageView>(R.id.closeButton)
        val titleTextView = dialogView.findViewById<TextView>(R.id.titleTextView)

        /*  // Retrieve the dialogText from intent extras
          val dialogText = intent.getStringExtra("dialogText")

          // Set the content of the dialog using dialogText
          infoTextView.text = dialogText*/
        titleTextView.text =getString(R.string.information)

        val dialogBuilder = AlertDialog.Builder(this, R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Handle the close button click
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }
}