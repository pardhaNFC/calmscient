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
import com.calmscient.databinding.FragmentGadQuestionsBinding
import com.calmscient.utils.common.SavePreferences

class GADQuestionFragment : Fragment() {

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var binding: FragmentGadQuestionsBinding
    lateinit var savePrefData: SavePreferences
    private var currentQuestionIndex = 0
    private var isPreviousButtonVisible = false
    private var isNextButtonVisible = true // Initially, show the next button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            loadFragment(ScreeningsFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGadQuestionsBinding.inflate(inflater, container, false)
        savePrefData = SavePreferences(requireContext())
        binding.previousQuestion.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the selected title from arguments
        val selectedTitle = arguments?.getString("selectedTitle")

        // Update your UI with the selected title
        if (!selectedTitle.isNullOrEmpty()) {
            binding.tvGadtitle.text = selectedTitle
        }
        val titleG = "GAD-7"
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        questionAdapter = QuestionAdapter(requireContext(), questions, titleG)
        binding.questionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }
// Create an instance of the CommonDialog class
        val commonDialog = CommonDialog(requireContext())

        // Show a dialog when the fragment is loaded
        commonDialog.showDialog(getString(R.string.gad))
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
        if (savePrefData.getSpanLanguageState() == true) {
            val questionTexts = listOf(
                "1. Se ha sentido nervioso(a), ansioso(a) o con los nervios de punta",
                "2. No ha sido capaz de parar o controlar su preocupación",
                "3. Se ha preocupado demasiado por motivos diferentes",
                "4. Ha tenido dificultad para relajarse",
                "5. Se ha sentido tan inquieto(a) que no ha podido quedarse quieto(a)",
                "6. Se ha molestado o irritado fácilmente",
                "7. Ha tenido miedo de que algo terrible fuera a pasar",
                "8. If you checked off any problems, how difficult have these problems made it for you to do your work, take care of things at home, or get along with other people?"
            )
            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options = if (index == 7) {
                    // Custom options for the 8th question
                    listOf(
                        "No es nada difícil",//Not difficult at all
                        "Algo dificil",//Somewhat difficult
                        "Very difficult", //Very difficult
                        "Extremadamente difícil"//Extremely difficult
                    )
                } else {
                    // Default options for other questions
                    listOf(
                        "Ningún día",
                        "Varios días",
                        "Más de la mitad de los días",
                        "Casi todos los días "
                    )
                }
                questionsList.add(Question(questionText, options))
            }
        } else {
            val questionTexts = listOf(
                "1. Feeling nervous, anxious or on edge",
                "2. Not being able to stop or control worrying",
                "3. Worrying too much about different things",
                "4. Trouble relaxing",
                "5. Being so restless that it is hard to sit still",
                "6. Becoming easily annoyed or irritable",
                "7. Feeling afraid as if something awful might happen"
                //"8. If you checked off any problems, how difficult have these problems made it for you to do your work, take care of things at home, or get along with other people?"
            )
            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options = if (index == 7) {
                    // Custom options for the 8th question
                    listOf(
                        "Not difficult at all",
                        "Somewhat difficult",
                        "Very difficult",
                        "Extremely difficult"
                    )
                } else {
                    // Default options for other questions
                    listOf(
                        "Not at all",
                        "Several days",
                        "More than half the days",
                        "Nearly every day"
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

        binding.questionsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
            binding.questionsRecyclerView.smoothScrollToPosition(currentQuestionIndex)
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