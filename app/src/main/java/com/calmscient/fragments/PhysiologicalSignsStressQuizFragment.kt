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
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.ChangingResponseQuizAdapter
import com.calmscient.databinding.FragmentPhysiologicalSignsStressQuizBinding
import com.calmscient.utils.common.SavePreferences

class PhysiologicalSignsStressQuizFragment : Fragment() {
    private lateinit var questionAdapter: ChangingResponseQuizAdapter
    private lateinit var binding: FragmentPhysiologicalSignsStressQuizBinding
    lateinit var savePrefData: SavePreferences

    private var currentQuestionIndex = 0
    private var isPreviousButtonVisible = false
    private var isNextButtonVisible = true // Initially, show the next button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            loadFragment(ChangingYourResponseFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhysiologicalSignsStressQuizBinding.inflate(inflater, container, false)
        savePrefData = SavePreferences(requireContext())
        binding.previousQuestion.visibility = View.GONE

       var description = requireArguments().getString("description")
        binding.tvTitle.text = description
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the selected title from arguments
        val selectedTitle = arguments?.getString("description")
        // Update your UI with the selected title
        binding.tvTitle.text = selectedTitle
        val titleA = "AUDIT"
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        questionAdapter = ChangingResponseQuizAdapter(requireContext(), questions, titleA)
        binding.stressSignsQuestionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }

        // Use a PagerSnapHelper for snapping to a question's position
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.stressSignsQuestionsRecyclerView)
        setupNavigation()
        binding.backIcon.setOnClickListener {
            loadFragment(ChangingYourResponseFragment())
        }
    }

    private fun generateDummyQuestions(): List<Question> {
        val questionsList = mutableListOf<Question>()
        if (savePrefData.getSpanLanguageState() == true) {
            val questionTexts = listOf(
                "1. Headache",
                "2. Shuttering, stammering, tremors, or trembling of lips and hands",
                "3. Tinnitus (Ringing or buzzing in one or both of your ears)",
                "4. Blushing, cold sweats or sweaty hands",
                "5. Dizziness, brain fog",
                "6. Dry mouth, problem swallowing",
                "7. Rashes, itchiness, hives",
                "8. Heartburn, stomach pain, nausea",
                "9. Difficulty breathing, coughing",
                "10. Chest pain, palpitations",
                "11. Muscle pain, nerve pain without any medical explanation",
                "12. Low sexual desire or performance",
                "13. Backache, stiff neck or shoulders",
                "14. Trouble sleeping",
                "15. IBS (Irritable bowel syndrome), constipation",
                "16. UTI (Urinary tract infection)",
                "17. Infection or herpes sores" ,
                "18. Do you grind your teeth? Or have you been told by family members or a dentist that you are grinding your teeth?"
            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options = if (index == 14 || index == 15 || index == 16 || index == 17) {
                    // Custom options from the 14th and 17th question
                    listOf("Yes", "No")
                } else {
                    // Default options for other questions
                    listOf("Not bothered", "Bothered a little", "Bothered a lot")
                }
                questionsList.add(Question(questionText, options))
            }
        }else{
            val questionTexts = listOf(
                "1. Headache",
                "2. Shuttering, stammering, tremors, or trembling of lips and hands",
                "3. Tinnitus (Ringing or buzzing in one or both of your ears)",
                "4. Blushing, cold sweats or sweaty hands",
                "5. Dizziness, brain fog",
                "6. Dry mouth, problem swallowing",
                "7. Rashes, itchiness, hives",
                "8. Heartburn, stomach pain, nausea",
                "9. Difficulty breathing, coughing",
                "10. Chest pain, palpitations",
                "11. Muscle pain, nerve pain without any medical explanation",
                "12. Low sexual desire or performance",
                "13. Backache, stiff neck or shoulders",
                "14. Trouble sleeping",
                "15. IBS (Irritable bowel syndrome), constipation",
                "16. UTI (Urinary tract infection)",
                "17. Infection or herpes sores" ,
                "18. Do you grind your teeth? Or have you been told by family members or a dentist that you are grinding your teeth?"
            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options = if (index == 14 || index == 15 || index == 16 || index == 17) {
                    // Custom options from the 14th and 17th question
                    listOf("Yes", "No")
                } else {
                    // Default options for other questions
                    listOf("Not bothered", "Bothered a little", "Bothered a lot")
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
        binding.stressSignsQuestionsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
            binding.stressSignsQuestionsRecyclerView.smoothScrollToPosition(currentQuestionIndex)
        } else {
            if (currentQuestionIndex == questions.size - 1) {
                //loadFragment(ResultsFragment())
                showResult()
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