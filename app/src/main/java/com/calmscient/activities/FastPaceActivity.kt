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
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.AnxietyQuestionsAdapter
import com.calmscient.databinding.FastPaceActivityBinding
import com.calmscient.fragments.BottomSheetFragment
import com.calmscient.fragments.DiscoveryFragment
import com.calmscient.fragments.ReminderBottomSheet
import com.kofigyan.stateprogressbar.StateProgressBar

class FastPaceActivity : AppCompatActivity(){
    private lateinit var binding: FastPaceActivityBinding


    private val maxProgress = 99
    private lateinit var progressBar1: StateProgressBar
    private lateinit var progressBar2: StateProgressBar
    private lateinit var progressBar3: StateProgressBar

    private var selectedOptionIndexSection1 = -1
    private var selectedOptionIndexSection2 = -1

    private var isLayoutOneVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FastPaceActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


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



        val title = intent.getStringExtra("description")
        // Find your ProgressBar by its ID
        progressBar1 = findViewById(R.id.your_state_progress_bar_id_1)
        progressBar2 = findViewById(R.id.your_state_progress_bar_id_2)

        //progressBar1 = findViewById(R.id.your_state_progress_bar_1)

        binding.tvTitlePlayer.text = title
        // binding.tvTitle3.text = title

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
            startActivity(Intent(this,GlossaryActivity::class.java))
        }
        binding.menuIcon.setOnClickListener {
            onBackPressed()
        }
        binding.reminderToggleButton.setOnClickListener {
            if(binding.reminderToggleButton.labelOn == "ON"){
                //bottomSheetDialog.show()
                // Toast.makeText(applicationContext, "Coming Soon", Toast.LENGTH_SHORT).show()
                val bottomSheetFragment = ReminderBottomSheet()
                bottomSheetFragment.show(this.supportFragmentManager, bottomSheetFragment.tag)
            }else{

            }
        }
        /* binding.icGlossary3.setOnClickListener {
             startActivity(Intent(this,GlossaryActivity::class.java))
         }
         binding.menuIcon3.setOnClickListener {
             onBackPressed()
         }*/


        binding.submitButton.setOnClickListener{
            showInformationDialog()
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(DiscoveryFragment::class.java.simpleName)
        if (fragment != null && fragment.isVisible) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showInformationDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.anixity_dialog, null)
        val infoTextView = dialogView.findViewById<TextView>(R.id.dialogTextView)
        val closeButton = dialogView.findViewById<ImageButton>(R.id.closeButton)

        // Retrieve the dialogText from intent extras
        val dialogText = "Great! \nYou’ve taken some positive action, and that’s a bigger deal than you might think.\n"

        // Set the content of the dialog using dialogText
        infoTextView.text = dialogText

        val dialogBuilder = AlertDialog.Builder(this, R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Handle the close button click
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun selectOptionSection1(index: Int, textViews: List<TextView>) {
        if (index != selectedOptionIndexSection1) {
            // Unselect the previously selected TextView in section 1
            selectedOptionIndexSection1 = index
            clearSelectionSection1(textViews)

            // Select the clicked TextView in section 1
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
            // Unselect the previously selected TextView in section 2
            selectedOptionIndexSection2 = index
            clearSelectionSection2(textViews)

            // Select the clicked TextView in section 2
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
}