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
import com.calmscient.databinding.FragmentEmotionalSignsStressQuizBinding
import com.calmscient.databinding.FragmentPhysiologicalSignsStressQuizBinding
import com.calmscient.utils.common.SavePreferences

class EmotionalSignsStressQuizFragment : Fragment() {
    private lateinit var questionAdapter: ChangingResponseQuizAdapter
    private lateinit var binding: FragmentEmotionalSignsStressQuizBinding
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
        binding = FragmentEmotionalSignsStressQuizBinding.inflate(inflater, container, false)
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
        binding.emotionalStressSignsQuestionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }

        // Use a PagerSnapHelper for snapping to a question's position
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.emotionalStressSignsQuestionsRecyclerView)
        setupNavigation()
        binding.backIcon.setOnClickListener {
            loadFragment(ChangingYourResponseFragment())
        }
    }

    private fun generateDummyQuestions(): List<Question> {
        val questionsList = mutableListOf<Question>()
        if (savePrefData.getSpanLanguageState() == true) {
            val questionTexts = listOf(
                "1. Increased anger, frustration, hostility",
                "2.Hyper vigilant, sensitive",
                "3. Increased irritability, edginess, impatience",
                "4. Overreaction to small annoyances",
                "5. Increased or decreased appetite",
                "6. Anxiety, worry, guilt, nervousness",
                "7. Feeling overloaded, overwhelmed",
                "8. Feeling of loneliness, worthlessness",
                "9. Feeling hopeless and helpless",
                "10. Difficulty concentrating",
                "11. Forgetfulness, disorganization, confusion",
                "12. Difficulty making decisions",
                "13. Difficulty learning new information",
                "14. Rumination",
                "15.  Panic/anxiety attacks",
                "16. Great sadness",
                "17. Frequent crying, sensitive to constructive criticism",
                "18. Increased fear",
                "19. Feeling numb/dissociated",
                "20. Depression, frequent mood swings",
            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options =   listOf("Not at all", "Occasionally", "Often","Nearly everyday")

                questionsList.add(Question(questionText, options))
            }
        }else{
            val questionTexts = listOf(
               "1. Increased anger, frustration, hostility",
                "2.Hyper vigilant, sensitive",
                "3. Increased irritability, edginess, impatience",
                "4. Overreaction to small annoyances",
                "5. Increased or decreased appetite",
                "6. Anxiety, worry, guilt, nervousness",
                "7. Feeling overloaded, overwhelmed",
                "8. Feeling of loneliness, worthlessness",
                "9. Feeling hopeless and helpless",
                "10. Difficulty concentrating",
                "11. Forgetfulness, disorganization, confusion",
                "12. Difficulty making decisions",
                "13. Difficulty learning new information",
                "14. Rumination",
                "15.  Panic/anxiety attacks",
                "16. Great sadness",
                "17. Frequent crying, sensitive to constructive criticism",
                "18. Increased fear",
                "19. Feeling numb/dissociated",
                "20. Depression, frequent mood swings",
            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options =   listOf("Not at all", "Occasionally", "Often","Nearly everyday")
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
        binding.emotionalStressSignsQuestionsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
            binding.emotionalStressSignsQuestionsRecyclerView.smoothScrollToPosition(currentQuestionIndex)
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