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

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.AnxietyQuestionsAdapter
import com.calmscient.activities.GlossaryActivity
import com.calmscient.databinding.FastPaceActivityBinding
import com.kofigyan.stateprogressbar.StateProgressBar

class FastPaceFragment : Fragment() {
    private lateinit var binding: FastPaceActivityBinding
    private val maxProgress = 99
    private lateinit var progressBar1: StateProgressBar
    private lateinit var progressBar2: StateProgressBar
    private lateinit var progressBar3: StateProgressBar

    private var selectedOptionIndexSection1 = -1
    private var selectedOptionIndexSection2 = -1

    private var isLayoutOneVisible = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FastPaceActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var layoutOne = binding.layoutOne
        var layoutTwo = binding.layoutTwo
        var layoutThree = binding.layoutThree

        val morningTextView = binding.morning
        val lunchTimeTextView = binding.lunchTime
        val eveningTextView = binding.evening

        val bedroomTextView = binding.myBedroom
        val couchTextView = binding.onMyCouch
        val carTextView = binding.myCar
        val patioTextView = binding.onPatio

        val title = arguments?.getString("description")

        progressBar1 = view.findViewById(R.id.your_state_progress_bar_id_1)
        progressBar2 = view.findViewById(R.id.your_state_progress_bar_id_2)

        binding.tvTitlePlayer.text = title

        morningTextView.setOnClickListener {
            selectOptionSection1(0, listOf(morningTextView, lunchTimeTextView, eveningTextView))
        }

        lunchTimeTextView.setOnClickListener {
            selectOptionSection1(1, listOf(morningTextView, lunchTimeTextView, eveningTextView))
        }

        eveningTextView.setOnClickListener {
            selectOptionSection1(2, listOf(morningTextView, lunchTimeTextView, eveningTextView))
        }

        bedroomTextView.setOnClickListener {
            selectOptionSection2(0, listOf(bedroomTextView, couchTextView, carTextView, patioTextView))
        }

        couchTextView.setOnClickListener {
            selectOptionSection2(1, listOf(bedroomTextView, couchTextView, carTextView, patioTextView))
        }

        carTextView.setOnClickListener {
            selectOptionSection2(2, listOf(bedroomTextView, couchTextView, carTextView, patioTextView))
        }

        patioTextView.setOnClickListener {
            selectOptionSection2(3, listOf(bedroomTextView, couchTextView, carTextView, patioTextView))
        }

        binding.nextScreen1.setOnClickListener {
            layoutOne.visibility = View.GONE
            layoutThree.visibility = View.GONE
            layoutTwo.visibility = View.VISIBLE
        }

        binding.nextScreen2.setOnClickListener {
            layoutTwo.visibility = View.GONE
            layoutThree.visibility = View.VISIBLE
            layoutOne.visibility = View.GONE
        }

        binding.previousScreen2.setOnClickListener {
            layoutTwo.visibility = View.GONE
            layoutThree.visibility = View.GONE
            layoutOne.visibility = View.VISIBLE
        }

        binding.previousScreen3.setOnClickListener {
            layoutOne.visibility = View.GONE
            layoutThree.visibility = View.GONE
            layoutTwo.visibility = View.VISIBLE
        }

        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.menuIcon.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.reminderToggleButton.setOnClickListener {
            if (binding.reminderToggleButton.labelOn == "ON") {
                val bottomSheetFragment = ReminderBottomSheet()
                bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
            }
        }

        binding.submitButton.setOnClickListener {
            showInformationDialog()
        }
    }

    private fun showInformationDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.anixity_dialog, null)
        val infoTextView = dialogView.findViewById<TextView>(R.id.dialogTextView)
        val closeButton = dialogView.findViewById<ImageButton>(R.id.closeButton)

        val dialogText = getString(R.string.fast_pase_card2_text3)

        infoTextView.text = dialogText

        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun selectOptionSection1(index: Int, textViews: List<TextView>) {
        if (index != selectedOptionIndexSection1) {
            selectedOptionIndexSection1 = index
            clearSelectionSection1(textViews)

            textViews[index].setBackgroundResource(R.drawable.anxiety_selected)
            textViews[index].setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    private fun clearSelectionSection1(textViews: List<TextView>) {
        textViews.forEachIndexed { i, textView ->
            if (i != selectedOptionIndexSection1) {
                textView.setBackgroundResource(R.drawable.anxiety_border)
                textView.setTextColor(Color.parseColor("#424242"))
            }
        }
    }

    private fun selectOptionSection2(index: Int, textViews: List<TextView>) {
        if (index != selectedOptionIndexSection2) {
            selectedOptionIndexSection2 = index
            clearSelectionSection2(textViews)

            textViews[index].setBackgroundResource(R.drawable.anxiety_selected)
            textViews[index].setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    private fun clearSelectionSection2(textViews: List<TextView>) {
        textViews.forEachIndexed { i, textView ->
            if (i != selectedOptionIndexSection2) {
                textView.setBackgroundResource(R.drawable.anxiety_border)
                textView.setTextColor(Color.parseColor("#424242"))
            }
        }
    }

    companion object {
        fun newInstance(description: String): FastPaceFragment {
            val fragment = FastPaceFragment()
            val args = Bundle()
            args.putString("description", description)
            fragment.arguments = args
            return fragment
        }
    }
}
