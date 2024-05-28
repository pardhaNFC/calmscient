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

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.databinding.FragmentDefenseResponseBinding
import com.calmscient.databinding.FragmentManagingStressToReduceAnxietyBinding


class DefenseResponseFragment : Fragment() {
    private lateinit var binding: FragmentDefenseResponseBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDefenseResponseBinding.inflate(inflater, container, false)
        val view = binding.root

        val title = arguments?.getString("description")

        binding.tvTitlePlayer.text = title

        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.nextQuestion.setOnClickListener{
            binding.screenOne.visibility = View.GONE
            binding.screenTwo.visibility = View.VISIBLE
        }
        binding.previousQuestion.setOnClickListener{
            binding.screenTwo.visibility = View.GONE
            binding.screenOne.visibility = View.VISIBLE
        }

        binding.imageViewFight.setOnClickListener{
            showInformationDialog(getString(R.string.defense_response_fight_dialog_desc),
                getString(R.string.fight_lowercase))
        }

        binding.imageViewFlight.setOnClickListener{
            showInformationDialog(
                getString(R.string.defense_response_flight_dialog_desc),
                getString(R.string.flight_lowercase))
        }
        binding.imageViewFreez.setOnClickListener{
            showInformationDialog(
                getString(R.string.defense_response_freez_dialog_desc),
                getString(R.string.freeze_lowercase))
        }
        binding.imageViewShutdown.setOnClickListener{
            showInformationDialog(
                getString(R.string.defense_response_shutdown_dialog_desc),
                getString(R.string.shutdown_lowercase))
        }
        return view
    }

    private fun showInformationDialog(dialogText : String, titleText : String) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.recognize_dialog, null)
        val infoTextView = dialogView.findViewById<TextView>(R.id.dialogTextView)
        val closeButton = dialogView.findViewById<ImageView>(R.id.closeButton)
        val titleTextView = dialogView.findViewById<TextView>(R.id.titleTextView)

        // Set the content of the dialog using dialogText
        infoTextView.text = dialogText
        titleTextView.text = titleText
        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Handle the close button click
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}