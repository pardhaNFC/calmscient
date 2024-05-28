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

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.databinding.ActivityAudioPlayerBinding
import com.calmscient.activities.WaveformView

class AudioPlayerFragment : Fragment(), MediaPlayer.OnPreparedListener,
    MediaPlayer.OnBufferingUpdateListener {

    private lateinit var binding: ActivityAudioPlayerBinding

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var waveformView: WaveformView
    private lateinit var playButton: ImageView
    private lateinit var handler: Handler
    private lateinit var title: TextView
    private var audioFilePath: String? = null
    private var isMediaPlayerInitialized = false
    var description: String? = null
    private  var tvdescription: String? = null

    private lateinit var loadingDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityAudioPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        description = requireArguments().getString("description")
        tvdescription = requireArguments().getString("summary")
        if (isNetworkConnected(requireContext())) {
            mediaPlayer = MediaPlayer()
            binding.tvTitle.text = description
            binding.tvDescription.text = tvdescription
            if (description == getString(R.string.meet_nora_austin)  ) {
                binding.audioScrollView.visibility = View.VISIBLE
                binding.tvDescription.text = tvdescription
            }
            if(tvdescription == getString(R.string.let_s_meet_nora_austin_and_melanie))
            {
                binding.audioScrollView.visibility = View.VISIBLE
                binding.informationIcon.isClickable = false

            }
            if(description == getString(R.string.what_s_causing_nora_austin_and_melanie_stress))
            {
                binding.audioScrollView.visibility = View.VISIBLE
                binding.informationIcon.isClickable = false
            }
            if (description == getString(R.string.anxiety_worry)) {
                binding.layoutBulb.visibility = View.VISIBLE
                binding.audioWorryTextView.text = getString(R.string.anxiety_audio_worry_text1)
                binding.worryScrollView.visibility = View.VISIBLE
            } else if (description == getString(R.string.moral_deficiency)) {
                binding.layoutBulb.visibility = View.VISIBLE
            } else if (description == getString(R.string.anxious_mind)) {
                binding.layoutBulb.visibility = View.VISIBLE
                binding.layoutCalmingText.visibility = View.VISIBLE
            }
            waveformView = view.findViewById(R.id.waveformView)
            playButton = view.findViewById(R.id.playButton)

            loadingDialog = ProgressDialog(requireContext())
            loadingDialog.setMessage("Loading Audio...")
            loadingDialog.setCanceledOnTouchOutside(false)
            binding.audioProgressBar.indeterminateDrawable =
                ContextCompat.getDrawable(requireContext(), R.drawable.circular_progressbar)
            audioFilePath = requireArguments().getString("audioResourceId")
            handler = Handler()
            mediaPlayer.setOnPreparedListener(this)
            mediaPlayer.setOnBufferingUpdateListener(this)

            binding.playButton.setOnClickListener {
                binding.audioProgressBar.visibility = View.VISIBLE
                if (!isMediaPlayerInitialized) {
                    mediaPlayer.setDataSource(audioFilePath)
                    mediaPlayer.prepareAsync()

                    mediaPlayer.setOnPreparedListener { mp ->
                        mp.start()
                        playButton.setImageResource(R.drawable.ic_audio_pause)
                        waveformView.visibility = View.VISIBLE
                        updateWaveformView()
                    }
                    mediaPlayer.setOnCompletionListener {
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
                    }
                }
            }
        } else {
            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
        }

        binding.audioBackward.setOnClickListener {
            skipBackward()
        }

        binding.audioForward.setOnClickListener {
            skipForward()
        }

        binding.menuIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.icGlossary.setOnClickListener {
            // isMediaPlayerInitialized = false
            //startActivity(Intent(this, GlossaryActivity::class.java))
            //startActivity(Intent(requireContext(), GlossaryActivity::class.java))
            loadFragment(GlossaryFragment())

        }

        if(tvdescription ==  getString(R.string.let_s_meet_nora_austin_and_melanie) || description ==  getString(R.string.what_s_causing_nora_austin_and_melanie_stress))
        {
            binding.informationIcon.isClickable = false;
        }
        else
        {
            binding.informationIcon.setOnClickListener {
                showInformationDialog()
            }
        }

        binding.bulbIcon.setOnClickListener {
            showInformationDialog()
        }
    }

    // Rest of your methods go here, unchanged from your original class

    /*private fun updateWaveformView() {
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
    }*/

    private fun updateWaveformView() {
        val audioDuration = mediaPlayer.duration
        val updateInterval = 100 // Update more frequently for smoother progression
        binding.audioProgressBar.visibility = View.GONE
        handler.post(object : Runnable {
            override fun run() {
                if (isMediaPlayerInitialized && mediaPlayer.isPlaying) {
                    val currentPosition = mediaPlayer.currentPosition
                    val progress = currentPosition.toFloat() / audioDuration.toFloat()

                    // Set the waveform progress
                    waveformView.setWaveformProgress(progress)

                    // Check if playback has completed
                    if (currentPosition < audioDuration) {
                        handler.postDelayed(this, updateInterval.toLong())
                    }
                } else {
                    // Handle the case where MediaPlayer is not initialized or not playing
                    // You can add appropriate logic here, such as resetting the waveform view.
                }
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        handler.removeCallbacksAndMessages(null)
    }

    /* override fun onBackPressed() {
         if (supportFragmentManager.backStackEntryCount > 0) {
             supportFragmentManager.popBackStack()
         } else {
             super.onBackPressed()
         }
     }*/

    private fun skipForward() {
        /*val currentPosition = mediaPlayer.currentPosition
        val newPosition = currentPosition + 10000 // Skip forward by 10 seconds (in milliseconds)
        if (newPosition <= mediaPlayer.duration) {
            mediaPlayer.seekTo(newPosition)
        }*/
        val currentPosition = mediaPlayer.currentPosition
        val newPosition = currentPosition + 10000

        binding.audioProgressBar.visibility = View.VISIBLE

        if (newPosition <= mediaPlayer.duration) {
            mediaPlayer.seekTo(newPosition)
        }

        mediaPlayer.setOnSeekCompleteListener {

            binding.audioProgressBar.visibility = View.GONE
        }
    }

    private fun skipBackward() {
        /*val currentPosition = mediaPlayer.currentPosition
        val newPosition = currentPosition - 10000 // Skip backward by 10 seconds (in milliseconds)
        if (newPosition >= 0) {
            mediaPlayer.seekTo(newPosition)
        }*/
        val currentPosition = mediaPlayer.currentPosition
        val newPosition = currentPosition - 10000

        binding.audioProgressBar.visibility = View.VISIBLE

        if (newPosition >= 0) {
            mediaPlayer.seekTo(newPosition)
        }
        mediaPlayer.setOnSeekCompleteListener {
            binding.audioProgressBar.visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        if (isMediaPlayerInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
        isMediaPlayerInitialized = false
    }

    private fun showInformationDialog() {
        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.audio_information_dialog, null)
        val closeButton = dialogView.findViewById<ImageView>(R.id.closeButton)
        val titleTextView = dialogView.findViewById<TextView>(R.id.titleTextView)
        val messageTextView = dialogView.findViewById<TextView>(R.id.tvInfo)
        val infoScroll = dialogView.findViewById<ScrollView>(R.id.scrollView)
        val calmLinearLayout = dialogView.findViewById<LinearLayout>(R.id.layoutCalm)

        titleTextView.text = getString(R.string.information)
        if (description == getString(R.string.meet_nora_austin)) {
            infoScroll.visibility = View.VISIBLE
            calmLinearLayout.visibility = View.GONE
        }
        if (description == getString(R.string.anxiety_worry)) {
            infoScroll.visibility = View.GONE
            calmLinearLayout.visibility = View.VISIBLE
            messageTextView.text = getString(R.string.information_calm)
        }
        if (description == getString(R.string.moral_deficiency)) {
            infoScroll.visibility = View.GONE
            calmLinearLayout.visibility = View.VISIBLE
            messageTextView.text = getString(R.string.information_moral)
        } else if (description == getString(R.string.anxious_mind)) {
            infoScroll.visibility = View.GONE
            calmLinearLayout.visibility = View.VISIBLE
            messageTextView.text = getString(R.string.information_anxious_mind)
        }
        val dialogBuilder = context?.let {
            AlertDialog.Builder(it, R.style.CustomDialog)
                .setView(dialogView)
        }

        val dialog = dialogBuilder?.create()
        dialog?.show()

        // Handle the close button click
        closeButton.setOnClickListener {
            dialog?.dismiss()
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

    private fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
