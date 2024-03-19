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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.CommonDialog
import com.calmscient.adapters.QuestionAdapter
import com.calmscient.adapters.YourStressTriggerQuizAdapter
import com.calmscient.databinding.FragmentGadQuestionsBinding
import com.calmscient.databinding.FragmentWorkRelatedStressQuizBinding
import com.calmscient.utils.common.SavePreferences

class WorkRelatedStressQuizFragment : Fragment() {

    private lateinit var questionAdapter: YourStressTriggerQuizAdapter
    private lateinit var binding: FragmentWorkRelatedStressQuizBinding
    lateinit var savePrefData: SavePreferences
    private var currentQuestionIndex = 0
    private var isPreviousButtonVisible = false
    private var isNextButtonVisible = true // Initially, show the next button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            loadFragment(YourStressTriggersQuizFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkRelatedStressQuizBinding.inflate(inflater, container, false)
        savePrefData = SavePreferences(requireContext())
        binding.previousQuestion.visibility = View.GONE

        binding.backIcon.setOnClickListener{
            loadFragment(YourStressTriggersQuizFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val titleG = "GAD-7"
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        questionAdapter = YourStressTriggerQuizAdapter(requireContext(), questions, titleG)
        binding.workRelatedStressRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }

        // Use a PagerSnapHelper for snapping to a question's position
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.workRelatedStressRecyclerView)
        setupNavigation()
        binding.backIcon.setOnClickListener {
            loadFragment(YourStressTriggersQuizFragment())
        }
    }

    private fun generateDummyQuestions(): List<Question> {
        val questionsList = mutableListOf<Question>()
        if (savePrefData.getSpanLanguageState() == true) {
            val questionTexts = listOf(
                "1. Have you lost your job recently? Or have been unemployed? (*if user answer YES, jump to the section 2)",
                "2. Do you feel unsatisfied with work?",
                "3. Do you feel your job description or responsibilities at work are unclear?",
                "4. Do you feel overwhelmed by your job or workload?",
                "5. Are you experiencing difficulty with the amount of hours you work or your work schedule?",
                "6. Have you been treated poorly at work?",
                "7. Are you experiencing difficulty with the responsibilities you have?"
            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options = if (index == 0) {
                    // Custom options for the 8th question
                    listOf(
                        "Yes",
                        "No"
                    )
                } else {
                    // Default options for other questions
                    listOf(
                        "No",
                        "Sometimes",
                        "Often",
                        "Constantly"
                    )
                }
                questionsList.add(Question(questionText, options))
            }
        } else{
            val questionTexts = listOf(
                "1. Have you lost your job recently? Or have been unemployed? (*if user answer YES, jump to the section 2)",
                "2. Do you feel unsatisfied with work?",
                "3. Do you feel your job description or responsibilities at work are unclear?",
                "4. Do you feel overwhelmed by your job or workload?",
                "5. Are you experiencing difficulty with the amount of hours you work or your work schedule?",
                "6. Have you been treated poorly at work?",
                "7. Are you experiencing difficulty with the responsibilities you have?"
            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options = if (index == 0) {
                    // Custom options for the 8th question
                    listOf(
                        "Yes",
                        "No"
                    )
                } else {
                    // Default options for other questions
                    listOf(
                        "No",
                        "Sometimes",
                        "Often",
                        "Constantly"
                    )
                }
                questionsList.add(Question(questionText, options))
            }

        }

        return questionsList
    }

    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex + 1)
        }

        binding.previousQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex - 1)
        }

        binding.workRelatedStressRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Check if the user is scrolling horizontally
                if (Math.abs(dx) > Math.abs(dy)) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    // Update the current question index
                    currentQuestionIndex = firstVisibleItemPosition
                    // Toggle the visibility of the buttons based on the current index
                    toggleButtonVisibility()
                }
            }
        })
    }


    private fun navigateToQuestion(index: Int) {
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        if (index in 0 until questions.size) {
            currentQuestionIndex = index
            binding.workRelatedStressRecyclerView.smoothScrollToPosition(currentQuestionIndex)
        } else {
            if (currentQuestionIndex == questions.size - 1) {
                loadFragment(ResultsFragment())
            }
        }
    }

    private fun showResult() {
        val toastMessage = "You've reached the end of the questions!"
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun loadFragment(fragment: Fragment) {

        val bundle = Bundle()
        bundle.putString("description", getString(R.string.work_related_stress_quiz))
        fragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun toggleButtonVisibility() {
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        isPreviousButtonVisible = currentQuestionIndex > 0
        isNextButtonVisible = currentQuestionIndex < totalQuestions - 1


        // Always show both "Previous" and "Next" buttons/icons for the last question
        if (currentQuestionIndex >= questions.size - 1) {
            isPreviousButtonVisible = true
            isNextButtonVisible = true
        }
        binding.previousQuestion.visibility =
            if (isPreviousButtonVisible) View.VISIBLE else View.GONE
        binding.nextQuestion.visibility = if (isNextButtonVisible) View.VISIBLE else View.GONE
    }

}