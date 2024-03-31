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
import com.calmscient.activities.CommonDialog
import com.calmscient.adapters.QuestionAdapter
import com.calmscient.databinding.FragmentADUITQuestionBinding
import com.calmscient.utils.common.SavePreferences

class AUDITQuestionFragment : Fragment() {
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var binding: FragmentADUITQuestionBinding
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
        binding = FragmentADUITQuestionBinding.inflate(inflater, container, false)
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
            binding.titleTextView.text = selectedTitle
        }
        val titleA = "AUDIT"
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        questionAdapter = QuestionAdapter(requireContext(), questions, titleA)
        binding.questionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }
        // Create an instance of the CommonDialog class
        val commonDialog = CommonDialog(requireContext())

        // Show a dialog when the fragment is loaded
        commonDialog.showDialog(getString(R.string.audit))
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
                "1. ¿Con qué frecuencia bebe un trago con contenido de alcohol?",
                "2. ¿Cuántos tragos que contengan alcohol consume en un día típico cuando está bebiendo?",
                "3. ¿Con qué frecuencia bebe cuatro o más tragos en una ocasión?",
                "4. ¿Con qué frecuencia durante el último año se dio cuenta de que no pudo dejar de beber una vez que había empezado?",
                "5. ¿Con qué frecuencia durante el último año ha dejado de hacer lo que normalmente se esperaba de usted debido a la bebida?",
                "6. ¿Con qué frecuencia durante el último año ha necesitado un primer trago en la mañana para ponerse en acción después de una sesión de beber abundantemente?",
                "7. ¿Con qué frecuencia durante el último año ha tenido una sensación de culpa o remordimiento después de beber?",
                "8. ¿Con qué frecuencia durante el último año ha sido incapaz de recordar lo que pasó la noche anterior debido a su forma de beber?",
                "9. ¿Usted o alguien más han resultado heridos debido a su forma de beber?",
                "10. ¿Ha estado preocupado por su forma de beber o le ha sugerido que beba menos algún pariente, amigo, médico u otro trabajador de la atención a la salud?"
            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options = if (index == 0) {
                    // Custom options for the 1st question
                    listOf(
                        "Nunca",
                        "Una vez al mes o menos",
                        "2-4 veces al mes",
                        "2-3 veces por semana",
                        "4 o más veces por semana"
                    )
                } else if (index == 1) {
                    // Custom options for the 2nd question
                    listOf("0- 2", "3 o 4", "5 o 6", "7 - 9", "10 o más")
                } else if (index == 8 || index == 9) {
                    // Custom options for the 9th and 10th question
                    listOf("No", "Sí, pero no en el último año", "Sí, en el último año")
                } else {
                    // Default options for other questions
                    listOf("Nunca", "Menos de una vez al mes", "Mensualmente", "Semanalmente", "Diario o casi a diario")
                }
                questionsList.add(Question(questionText, options))
            }
        }else{
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
                    listOf("No", "Yes, but not in the past year", "Yes, during the past year")
                } else {
                    // Default options for other questions
                    listOf("Never", "Less than monthly", "Monthly", "Weekly", "Daily or almost daily")
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
        val bundle = Bundle()
        bundle.putString("description", getString(R.string.your_results))
        bundle.putInt(ResultsFragment.SOURCE_SCREEN_KEY, ResultsFragment.SCREENINGS_FRAGMENT)
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