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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.QuestionAdapter
import com.calmscient.databinding.FragmentADUITQuestionBinding
import com.calmscient.databinding.FragmentGadQuestionsBinding

class ADUITQuestionFragment : Fragment() {
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var binding: FragmentADUITQuestionBinding
    private val questions: List<Question> = generateDummyQuestions()
    private var currentQuestionIndex = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentADUITQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Get the selected title from arguments
        val selectedTitle = arguments?.getString("selectedTitle")

        // Update your UI with the selected title
        if (!selectedTitle.isNullOrEmpty()) {
            binding.titleTextView.text = selectedTitle
        }

        questionAdapter = QuestionAdapter(questions)
        binding.questionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }
        // Use a PagerSnapHelper for snapping to a question's position
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.questionsRecyclerView)
        setupNavigation()
        binding.backIcon.setOnClickListener {
            loadFragment(ScreeningsFragment())
        }
    }


    private fun generateDummyQuestions(): List<Question> {
        val questionsList = mutableListOf<Question>()

        val questionTexts = listOf(
            "1. How often do you have a drink containing alcohol?",
            "2. How many standard drinks containing alcohol do you have on a typical day when drinking?",
            "3. How often do you have six or more drinks on one occasion?",
            "4. During the past year, how often have you found that you were not able to stop drinking once you had started?",
            "5. During the past year, how often have you failed to do what was normally expected of you because of drinking?",
            "6. During the past year, how often have you needed a drink in the morning to get yourself going after a heavy drinking session?",
            "7. During the past year, how often have you had a feeling of guilt or remorse after drinking?",
            "8. During the past year, how often have you been unable to remember what happened the night before because you had been drinking?",
            "9. Have you or someone else been injured as a result of your drinking?",
            "10. Has a relative or friend, doctor or other health worker been concerned about your drinking or suggested you cut down?"


        )

        for (index in questionTexts.indices) {
            val questionText = questionTexts[index]
            val options = if (index == 0) {
                // Custom options for the 1st question
                listOf(
                    "Never",
                    "Monthly or less",
                    "2-4 times a month",
                    "2-3 times a week",
                    "4 or more times a week"
                )
            } else if (index == 1) {
                // Custom options for the 2nd question
                listOf("1 or 2", "3 or 4", "5 or 6", "7 to 9", "10 or more")
            } else if (index == 8 || index == 9) {
                // Custom options for the 9th and 10th question
                listOf("No (0)", "Yes, but not in the past year (2)", "Yes, during the past year ")
            } else {
                // Default options for other questions
                listOf("Never", "Less than monthly", "Monthly", "Weekly", "Daily or almost daily")
            }
            questionsList.add(Question(questionText, options))
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

        binding.questionsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Check if the user is scrolling horizontally
                if (Math.abs(dx) > Math.abs(dy)) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (firstVisibleItemPosition != currentQuestionIndex) {
                        currentQuestionIndex = firstVisibleItemPosition
                    }
                }
            }
        })
    }

    private fun navigateToQuestion(index: Int) {
        if (index in 0 until questions.size) {
            currentQuestionIndex = index
            binding.questionsRecyclerView.smoothScrollToPosition(currentQuestionIndex)
        }else{
            if(currentQuestionIndex == questions.size-1){
                loadFragment(ResultsFragment())
            }
        }
    }
    private fun showResult() {
        val toastMessage = "You've reached the end of the questions!"
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.commit()
    }
}