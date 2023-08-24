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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.AnxietyQuestionsAdapter
import com.calmscient.databinding.ManageanxietyQuestionsBinding
import com.calmscient.fragments.DiscoveryFragment

data class AnxietyTextDataClass(
    val text1: String?,
    val text2: String?,
    val anxietybulb: Int?,
    val imageanxiety: Int?,
    val text3: String?,
    val text4: String?,
    var selectedOption: Int = -1
)

class AnxietyQuestionsActivity : AppCompatActivity() {
    private lateinit var binding: ManageanxietyQuestionsBinding
    private lateinit var anxietyadapter: AnxietyQuestionsAdapter
    private val anxietyText = mutableListOf<AnxietyTextDataClass>()
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ManageanxietyQuestionsBinding.inflate(layoutInflater)
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
            findViewById(R.id.step6Indicator),
            findViewById(R.id.step7Indicator),
            findViewById(R.id.step8Indicator),
            findViewById(R.id.step9Indicator),
            findViewById(R.id.step10Indicator)
        )
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
        binding.optionsRecyclerView1.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        anxietyadapter = AnxietyQuestionsAdapter(anxietyText)
        binding.optionsRecyclerView1.adapter = anxietyadapter
    }

    private fun displayCardViews() {
        anxietyText.add(
            AnxietyTextDataClass(
                getString(R.string.page_1_1),
                "An estimated 31.1% of U.S. adults experience any anxiety disorder at some time in their lives.",
                R.drawable.ic_bulb_anxiety,
                R.drawable.ic_anxietyquestion_image,
                "The Calmscient discovery will only be as effective as you make it. So be determined to dedicate time to following along and completing.",
                "You're not alone"
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                getString(R.string.page_2_1),
                "Anxiety is a helpful survival mechanism",
                R.drawable.ic_bulb_anxiety,
                R.drawable.ic_anxietyquestion_image,
                "Imagine an early human trying to survive out in the woods. As in most forms of life, several built-in survival mechanisms are passed along from one generation to the next through DNA, and are an important part of the survival of a species.",
                null
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                "Some of the responses to this survival mechanism are:",
                R.drawable.ic_bulb_anxiety,
                R.drawable.ic_anxietyquestion_image,
                null,
                "FIGHT\n Face the threat head on!"
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                R.drawable.ic_bulb_anxiety,
                R.drawable.ic_anxietyquestion_image,
                null,
                "FLIGHT\n Look for a way out!"
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                R.drawable.ic_bulb_anxiety,
                R.drawable.ic_anxietyquestion_image,
                null,
                "FREEZE\nDon’t move!"
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                "But now imagine this person coming face-to-face with a hungry bear. Fight, Flight, or Freeze may not be enough to survive this encounter! A bear is much stronger, faster, and smarter for either of those responses.",
                R.drawable.ic_bulb_anxiety,
                R.drawable.ic_anxietyquestion_image,
                null,
                "This is where the “Worry” survival\n mechanism comes into play."
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                getString(R.string.page_7_1),
                " “Worry” could help them take measures to avoid or back away from dangers before they became life threatening.",
                R.drawable.ic_bulb_anxiety,
                R.drawable.ic_anxietyquestion_image,
                "In this example, if the person spotted a hungry bear from a distance, they would remember the potential danger and take another route, or possibly remember and avoid the places where bears were actively hunting for food. In both cases, \n" + "“Worry” would help them survive successfully.",
                "So, worrying is a helpful skill! "
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                "But how does too much “Worry” affect us?",
                null,
                R.drawable.ic_bulb_anxiety,
                R.drawable.ic_anxietyquestion_image,
                null,
                "We often called this excess of worry “Anxiety”."
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                "In our modern world, there are many bear-like situations we face every day.",
                "It’s easy to become conditioned to constantly trigger our “Worry” response in order to avoid the need to “Fight, Flight or Freeze”. \n" +
                        "But overprotecting ourselves with anxiety often comes at the expense of our happiness. ",
                R.drawable.ic_bulb_anxiety,
                R.drawable.ic_anxietyquestion_image,
                "Excessive use of worry, rumination, or fear of being at the wrong place at the wrong time limits our ability to enjoy life.\n",
                null
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                "Remember: worry and anxiety are not your enemies! ",
                "The key to more peace of mind lies in enhancing your understanding of both and then learning the skills to use them in the right moments and for the right amounts of time.\n",
                R.drawable.ic_bulb_anxiety,
                R.drawable.ic_anxietyquestion_image,
                null,
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