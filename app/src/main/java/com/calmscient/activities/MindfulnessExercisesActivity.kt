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
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.MindfulnessScreensAdapter
import com.calmscient.data.remote.MindfulnessExercisesTextDataClass
import com.calmscient.databinding.MindfulnesscreensBinding
import com.calmscient.fragments.DiscoveryFragment
class MindfulnessExercisesActivity : AppCompat() {
    private lateinit var binding: MindfulnesscreensBinding
    private lateinit var anxietyadapter: MindfulnessScreensAdapter
    private val anxietyText = mutableListOf<MindfulnessExercisesTextDataClass>()
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MindfulnesscreensBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.optionsRecyclerView1)

        // Find your ProgressBar by its ID
        progressBar = findViewById<ProgressBar>(R.id.progressBar2)

        // Set the initial progress
        progressBar.progress = currentQuestionIndex * (maxProgress / (anxietyText.size - 1))

        setupNavigation()
        initializeAdapter()
        displayCardViews()

        stepIndicators = listOf(
            findViewById(R.id.step1Indicator),
            findViewById(R.id.step2Indicator),
            findViewById(R.id.step3Indicator),
            findViewById(R.id.step4Indicator),
            findViewById(R.id.step5Indicator),
            findViewById(R.id.step6Indicator)
        )

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
        binding.optionsRecyclerView1.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        anxietyadapter = MindfulnessScreensAdapter(anxietyText)
        binding.optionsRecyclerView1.adapter = anxietyadapter
    }

    private fun displayCardViews() {
        anxietyText.add(
            MindfulnessExercisesTextDataClass(
                null,
                "Have you ever caught your mind wandering or daydreaming while you are in the middle of a familiar or repetitive task? You could be walking, working or even driving your car, and your mind is miles away, perhaps fantasizing about going on vacation, thinking about your to-do list, or worrying about some upcoming event. \n In either case, you are not focusing on the current situation and not in touch with the ‘here and now’.\n This mode of operation is often referred to as automatic pilot.",
                null,
                null,
                R.drawable.ic_anxietyquestion_image

            )
        );
        anxietyText.add(
            MindfulnessExercisesTextDataClass(
                null,
                "Mindfulness is the opposite of automatic pilot. It is about experiencing the world that is firmly in the ‘here and now’. This is referred to as the being mode. It liberates you from automatic and unhelpful thoughts and responses.",
                null,
                null,
                R.drawable.ic_anxietyquestion_image
            )
        );
        anxietyText.add(
            MindfulnessExercisesTextDataClass(
                getString(R.string.screen3_heading),
                getString(R.string.screen3_belowheading),
                null,
                null,
                R.drawable.ic_anxietyquestion_image

            )
        );
        anxietyText.add(
            MindfulnessExercisesTextDataClass(
                null,
                getString(R.string.screen4),
                null,
                null,
                R.drawable.ic_anxietyquestion_image
            )
        );
        anxietyText.add(
            MindfulnessExercisesTextDataClass(
                null,
                getString(R.string.screen5),
                null,
                null,
                R.drawable.ic_anxietyquestion_image
            )
        );
        anxietyText.add(
            MindfulnessExercisesTextDataClass(
                null,
                getString(R.string.screen6),
                R.drawable.exercises_bellicon,
                R.drawable.mindfullexercise_heart__image,
                R.drawable.ic_anxietyquestion_image
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

        binding.optionsRecyclerView1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Check if the user is scrolling horizontally
                if (Math.abs(dx) > Math.abs(dy)) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (firstVisibleItemPosition != currentQuestionIndex) {
                        previousQuestionIndex = currentQuestionIndex
                        currentQuestionIndex = firstVisibleItemPosition

                        // Calculate and set the progress based on the current question index
                        progressBar.progress =
                            currentQuestionIndex * (maxProgress / (anxietyText.size - 1))
                        // Update the step indicators (ImageViews) as active or inactive
                        updateStepIndicators()
                    }
                }
            }
        })
    }

    private fun updateStepIndicators() {
        // Update the previous step indicator to inactive
        /*if (previousQuestionIndex >= 0 && previousQuestionIndex < stepIndicators.size) {
            stepIndicators[previousQuestionIndex].setImageResource(R.drawable.ic_inactivetickmark)
        }*/
        // Update the current step indicator to active
        if (currentQuestionIndex >= 0 && currentQuestionIndex < stepIndicators.size) {
            stepIndicators[currentQuestionIndex].setImageResource(R.drawable.ic_activetickmark)
        }
    }

    private fun navigateToQuestion(index: Int, isNext: Boolean) {
        if (index in 0 until anxietyText.size) {
            if (isNext) {
                previousQuestionIndex = currentQuestionIndex
            } else {
                // Update the current step indicator to inactive when going to the previous question
                if (currentQuestionIndex >= 0 && currentQuestionIndex < stepIndicators.size) {
                    stepIndicators[currentQuestionIndex].setImageResource(R.drawable.ic_inactivetickmark)
                }
            }
            currentQuestionIndex = index
            binding.optionsRecyclerView1.smoothScrollToPosition(currentQuestionIndex)

            // Calculate and set the progress based on the current question index
            progressBar.progress = currentQuestionIndex * (maxProgress / (anxietyText.size - 1))

            // Update the step indicators (ImageViews) as active or inactive
            updateStepIndicators()
        }
    }

}
