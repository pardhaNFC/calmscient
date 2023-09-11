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
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.AnxietyQuestionsAdapter
import com.calmscient.data.remote.CardItemDataClass
import com.calmscient.databinding.ManageanxietyQuestionsBinding
import com.calmscient.fragments.DiscoveryFragment

data class AnxietyTextDataClass(
    val text1: String?,
    val text2: String?,
    val text3: String?,
    val text4: String?,
    val anxietybulb: Int?,
    val imageanxiety: Int?,
    val text5: String?,
    val text6: String?,
    val text7: String?,
    var selectedOption: Int = -1
)

class AnxietyQuestionsActivity : AppCompat() {
    private lateinit var binding: ManageanxietyQuestionsBinding

    private lateinit var anxietyadapter: AnxietyQuestionsAdapter
    private val anxietyText = mutableListOf<AnxietyTextDataClass>()
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private lateinit var progressBar: ProgressBar
    private lateinit var cardItemDataClass : CardItemDataClass


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

        binding.previousQuestion.visibility = View.GONE
        val title = intent.getStringExtra("description")

        binding.tvTitle.text = title
        // Find your ProgressBar by its ID
        progressBar = findViewById<ProgressBar>(R.id.progressBar2)

        // Set the initial progress
        progressBar.progress = currentQuestionIndex * (maxProgress / (anxietyText.size - 1))

        setupNavigation()
        initializeAdapter()
        if(title == getString(R.string.page_2_1))
        {
            displayAnxietyViews()
        }
        /* else if(title == "Get yourself out of “fast pace cycle”"){

             val intent = Intent(this, FastPaceActivity::class.java)
             intent.putExtra("description", card.description)
             this.startActivity(intent)
         }*/


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

    private fun displayAnxietyViews() {

        anxietyText.add(
            AnxietyTextDataClass(
                getString(R.string.anxiety_card1_text1),
                getString(R.string.anxiety_card1_text2),
                null,
                null,
                null,
                R.drawable.anxiety1,
                getString(R.string.anxiety_card1_text3),
                null,
                null
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                getString(R.string.anxiety_card2_text1),
                getString(R.string.anxiety_card2_text2),
                null,
                null,
                null,
                R.drawable.anxiety2,
                null,
                getString(R.string.anxiety_card2_text3),
                null
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                getString(R.string.anxiety_card3_text1),
                getString(R.string.anxiety_card3_text2),
                getString(R.string.anxiety_card3_text3),
                null,
                R.drawable.anxiety3,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                getString(R.string.anxiety_card4_text1),
                getString(R.string.anxiety_card4_text2),
                null,
                R.drawable.anxiety4,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                getString(R.string.anxiety_card5_text1),
                getString(R.string.anxiety_card5_text2),
                null,
                R.drawable.anxiety5,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                getString(R.string.anxiety_card6_text1),
                null,
                null,
                null,
                R.drawable.anxiety6,
                null,
                null,
                getString(R.string.anxiety_card6_text2)
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                getString(R.string.anxiety_card7_text1),
                null,
                null,
                null,
                R.drawable.anxiety7,
                null,
                getString(R.string.anxiety_card7_text2),
                getString(R.string.anxiety_card7_text3)
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                getString(R.string.anxiety_card8_text1),
                null,
                null,
                null,
                R.drawable.anxiety8,
                null,
                getString(R.string.anxiety_card8_text2),
                null
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                getString(R.string.anxiety_card9_text1),
                null,
                null,
                null,
                R.drawable.anxiety9,
                null,
                getString(R.string.anxiety_card9_text2),
                null
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                getString(R.string.anxiety_card10_text1),
                null,
                null,
                R.drawable.anxiety10,
                null,
                getString(R.string.anxiety_card10_text2),
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
        if(currentQuestionIndex ==0)
        {
            binding.previousQuestion.visibility = View.GONE
        }
        else
        {
            binding.previousQuestion.visibility = View.VISIBLE
        }

        if(currentQuestionIndex == stepIndicators.size-1)
        {
            binding.nextQuestion.visibility = View.GONE
        }
        else
        {
            binding.nextQuestion.visibility = View.VISIBLE
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