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
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.AnxietyQuestionsAdapter
import com.calmscient.databinding.FastPaceActivityBinding
import com.calmscient.fragments.DiscoveryFragment
import com.kofigyan.stateprogressbar.StateProgressBar

class FastPaceActivity: AppCompatActivity(){
    private lateinit var binding: FastPaceActivityBinding
    private lateinit var anxietyadapter: AnxietyQuestionsAdapter
    private val anxietyText = mutableListOf<AnxietyTextDataClass>()
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private lateinit var progressBar: StateProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FastPaceActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.optionsRecyclerViewFastPace)


        val title = intent.getStringExtra("description")
        // Find your ProgressBar by its ID
        progressBar = findViewById(R.id.your_state_progress_bar_id)

        binding.tvTitle.text = title


        val maxIndicators = 3

        // Create the stepIndicators list dynamically based on maxIndicators
        stepIndicators = (1..maxIndicators).map { stepIndex ->
            findViewById<ImageView>(resources.getIdentifier("step${stepIndex}Indicator", "id", packageName))
        }

        // Set the initial progress
        progressBar.animationDuration = currentQuestionIndex * (maxProgress / (anxietyText.size - 1))

        setupNavigation()
        initializeAdapter()
        if(title == "Get yourself out of “fast pace cycle”"){
            displayFastPaceViews()
            //Toast.makeText(this, "No Lesson - 2 Available", Toast.LENGTH_SHORT).show()
        }



        binding.icGlossary.setOnClickListener {
            startActivity(Intent(this,GlossaryActivity::class.java))
        }
        binding.menuIcon.setOnClickListener {
            onBackPressed()
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
    private fun initializeAdapter() {
        binding.optionsRecyclerViewFastPace.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        anxietyadapter = AnxietyQuestionsAdapter(anxietyText)
        binding.optionsRecyclerViewFastPace.adapter = anxietyadapter
    }



    private fun displayFastPaceViews() {

        anxietyText.add(
            AnxietyTextDataClass(
                null,
                getString(R.string.fast_pase_card1_text1),
                null,
                null,
                null,
                R.drawable.ic_anxietyquestion_image,
                getString(R.string.fast_pase_card1_text2),
                getString(R.string.fast_pase_card1_text3),
                null
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                getString(R.string.fast_pase_card1_text1),
                null,
                null,
                null,
                R.drawable.ic_anxietyquestion_image,
                getString(R.string.fast_pase_card1_text2),
                getString(R.string.fast_pase_card1_text3),
                null
            )
        );

        anxietyText.add(
            AnxietyTextDataClass(
                null,
                getString(R.string.fast_pase_card1_text1),
                null,
                null,
                null,
                R.drawable.ic_anxietyquestion_image,
                getString(R.string.fast_pase_card1_text2),
                getString(R.string.fast_pase_card1_text3),
                null
            )
        );



        anxietyadapter.notifyDataSetChanged()
    }

    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex + 1, true)
        }

        binding.previousQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex - 1, false)
        }

        binding.optionsRecyclerViewFastPace.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Check if the user is scrolling horizontally
                if (Math.abs(dx) > Math.abs(dy)) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (firstVisibleItemPosition != currentQuestionIndex) {
                        previousQuestionIndex = currentQuestionIndex
                        currentQuestionIndex = firstVisibleItemPosition

                        // progressBar.stateDescriptionColor = getColor(R.color.pink)
                        updateStepIndicators()


                    }
                }

            }
        })
    }


    private fun updateStepIndicators() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < stepIndicators.size) {
            // Update the current step indicator to active
            progressBar.setCurrentStateNumber(StateProgressBar.StateNumber.values()[currentQuestionIndex])
            // Update the state indicator colors
            progressBar.stateNumberBackgroundColor = getColor(R.color.pink)
            progressBar.stateNumberForegroundColor = getColor(R.color.white)
            //progressBar.stateF = getColor(R.color.pink)
            progressBar.stateDescriptionColor = getColor(R.color.pink)


        }

    }

    private fun navigateToQuestion(index: Int, isNext: Boolean) {
        if (index in 0 until anxietyText.size) {
            if (isNext) {
                previousQuestionIndex = currentQuestionIndex
            } else {
                // Update the current step indicator to inactive when going to the previous question
                if (currentQuestionIndex >= 0 && currentQuestionIndex < stepIndicators.size) {
                    //stepIndicators[currentQuestionIndex].setImageResource(R.drawable.ic_inactivetickmark)
                    //  progressBar.stateDescriptionColor = getColor(R.color.pink)
                }
            }
            currentQuestionIndex = index
            binding.optionsRecyclerViewFastPace.smoothScrollToPosition(currentQuestionIndex)

            updateStepIndicators()

        }
    }
}