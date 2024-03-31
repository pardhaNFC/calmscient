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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.databinding.FragmentBiasedThinkingStressBinding

class BiasedThinkingStressFragment : Fragment() {
    private lateinit var binding: FragmentBiasedThinkingStressBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBiasedThinkingStressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("description")
        binding.tvTitle.text = title

        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.menuIcon.setOnClickListener {
            loadFragment(ChangingYourResponseFragment())
        }

        setButtonClickListener(binding.btnAll, "All or nothing (black and white) thinking")
        setButtonClickListener(binding.btnCatas, "Catastrophizing")
        setButtonClickListener(binding.btnPerfect, "Perfectionist thinking")
        setButtonClickListener(binding.btnNegative, "Negative (mental) filtering")
        setButtonClickListener(binding.btnPersonalize, "Personalization and blame")
        setButtonClickListener(binding.btnJumping, "Jumping to conclusions")
        setButtonClickListener(binding.btnMaking, "Making negative assumptions")
        setButtonClickListener(binding.btnEmotional, "Emotional reasoning (“fear talk”)")



    }
    private fun setButtonClickListener(button: View, message: String) {
        button.setOnClickListener {
            showInformationDialog(requireContext(), message)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showInformationDialog(context: Context, message: String) {
        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.adapter_review_dialog, null)
        val title = dialogView.findViewById<TextView>(R.id.titleTextDialog)
        val tvInfo = dialogView.findViewById<TextView>(R.id.tvReviewInfo)
        val closeBtn = dialogView.findViewById<ImageView>(R.id.closeButton)

        title.text = message
        if (message.contains("All or nothing (black and white) thinking")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_one)
        } else if (message.contains("Catastrophizing")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_two)
        } else if (message.contains("Perfectionist thinking")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_three)
        } else if (message.contains("Negative (mental) filtering")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_four)
        } else if (message.contains("Personalization and blame")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_five)
        } else if (message.contains("Jumping to conclusions")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_six)
        } else if (message.contains("Making negative assumptions")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_seven)
        } else if (message.contains("Emotional reasoning (“fear talk”)")) {
            tvInfo.text = context.getString(R.string.anxiety_review_think_eight)
        }
        val dialogBuilder = context?.let {
            AlertDialog.Builder(it, R.style.CustomDialog)
                .setView(dialogView)
        }

        val dialog = dialogBuilder?.create()
        dialog?.show()

        // Handle the close button click
        closeBtn.setOnClickListener {
            dialog?.dismiss()
        }
    }
}