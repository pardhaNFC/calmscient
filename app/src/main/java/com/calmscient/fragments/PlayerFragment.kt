package com.calmscient.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.utils.common.SavePreferences
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class PlayerFragment : Fragment() {

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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_player, container, false)

        player = SimpleExoPlayer.Builder(requireContext()).build()

        savePrefData = SavePreferences(requireContext())
        playerView = rootView.findViewById(R.id.playerViewLayout)
        favoritesIcon = rootView.findViewById(R.id.favoritesIcon)
        heading = rootView.findViewById(R.id.headingTextView)
        summary = rootView.findViewById(R.id.summaryTextView)
        title = rootView.findViewById(R.id.tv_title_player)
        dialog_img = rootView.findViewById(R.id.informationIcon)
        dialogText = requireArguments().getString("dialogText").toString()
        if (savedInstanceState == null) {
            // Initialize ExoPlayer only if fragment is initially created

            /*  player = SimpleExoPlayer.Builder(requireContext()).build()*/

            val contentUri = requireArguments().getString("videoResourceId")
            val headingText = requireArguments().getString("heading")
            val summaryText = requireArguments().getString("summary")
            val videoResourceId = requireArguments().getInt("videoResourceId", 0)
            val mediaItem = if (contentUri != null) {
                MediaItem.fromUri(Uri.parse(contentUri))
            } else {
                val videoPath = "android.resource://${requireActivity().packageName}/$videoResourceId"
                MediaItem.fromUri(Uri.parse(videoPath))
            }

            // Setup ExoPlayer
            playerView.player = player
            player.setMediaItem(mediaItem)
            player.playWhenReady = true

            if (savePrefData.getAslLanguageState() == true) {
                heading.visibility = View.GONE
            } else {
                heading.visibility = View.VISIBLE
            }
            heading.text = headingText
            summary.text = summaryText
            title.text = headingText
        }
        if (dialogText.equals("null")) {
            dialog_img.visibility = View.GONE
        } else {
            dialog_img.visibility = View.VISIBLE
        }
        // Rest of your initialization code
        initializeBinding(rootView)
        initializeVideoControl()
        return rootView
    }

    @SuppressLint("MissingInflatedId")
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        val isPortrait = newConfig.orientation == Configuration.ORIENTATION_PORTRAIT

        // Change the layout based on the new orientation
        if (isPortrait) {
            requireActivity().setContentView(R.layout.activity_player)
            // Re-bind the views to the updated portrait layout
            playerView = requireView().findViewById(R.id.playerViewLayout)
            favoritesIcon = requireView().findViewById(R.id.favoritesIcon)
            heading = requireView().findViewById(R.id.headingTextView)
            summary = requireView().findViewById(R.id.summaryTextView)
            title = requireView().findViewById(R.id.tv_title_player)

            val headingText = requireArguments().getString("heading")
            val summaryText = requireArguments().getString("summary")
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
            initializeBinding(requireView())
            initializeVideoControl()
        } else {
            // Load the landscape layout (fragment_player_landscape.xml)
            requireActivity().setContentView(R.layout.activity_player_landscape)
            // Re-bind the landscape-only views (e.g., playerView and favoritesIcon)
            playerView = requireView().findViewById(R.id.playerViewLayout)
            favoritesIcon = requireView().findViewById(R.id.favoritesIcon)
            playerView.player = player

            // Initialize your landscape layout-specific control setup here
            initializeBinding(requireView())
            initializeVideoControl()
        }
    }

    override fun onPause() {
        super.onPause()
        playerView.player!!.playWhenReady = false
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

    private fun initializeBinding(rootView: View) {
        rootView.findViewById<ImageButton>(R.id.orientationBtn).setOnClickListener {
            toggleOrientation()
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            dialog_img.setOnClickListener {
                showInformationDialog()
            }
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rootView.findViewById<ImageView>(R.id.ic_glossary).setOnClickListener {
                // startActivity(Intent(requireContext(), GlossaryActivity::class.java))
                loadFragment(GlossaryFragment())
            }

            rootView.findViewById<ImageView>(R.id.menu_icon).setOnClickListener {
                requireActivity().onBackPressed()
            }
        }

        rootView.findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun toggleOrientation() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            requireActivity().setContentView(R.layout.activity_player_landscape) // Load landscape layout
        } else {
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            requireActivity().setContentView(R.layout.activity_player) // Load portrait layout
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
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.information_dialog, null)
        val infoTextView = dialogView.findViewById<TextView>(R.id.dialogInfoTextView)
        val closeButton = dialogView.findViewById<ImageView>(R.id.closeDialogButton)
        val titleTextView = dialogView.findViewById<TextView>(R.id.titleVideoDialog)

        // Set the content of the dialog using dialogText
        infoTextView.text = dialogText
        titleTextView.text = getString(R.string.information)
        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Handle the close button click
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    /* override fun onBackPressed() {
         // Handle the back button press within the fragment
         if (requireActivity().supportFragmentManager.backStackEntryCount > 0) {
             requireActivity().supportFragmentManager.popBackStack()
         } else {
             super.onBackPressed()
         }
     }*/

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
