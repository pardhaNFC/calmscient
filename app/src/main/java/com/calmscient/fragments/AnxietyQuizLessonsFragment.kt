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

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.adapters.AnxietyQuizLessonsAdapter
import com.calmscient.databinding.FragmentQuizLessonsBinding
import com.calmscient.di.remote.AnxietyLessonsQuizDataClass
import com.calmscient.utils.common.SavePreferences
import java.util.Arrays


class AnxietyQuizLessonsFragment : Fragment() {
    private lateinit var binding: FragmentQuizLessonsBinding
    lateinit var savePrefData: SavePreferences
    lateinit var quizAdapter: AnxietyQuizLessonsAdapter
    val quizAnxietyDataClass = mutableListOf<AnxietyLessonsQuizDataClass>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizLessonsBinding.inflate(inflater, container, false)
        savePrefData = SavePreferences(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initilizeAdapter()
        binding.menuIcon.setOnClickListener {
            // requireActivity().onBackPressed()
            //loadFragment(ManageAnxietyFragment())
            requireActivity().onBackPressed()
        }
    }

    private fun initilizeAdapter() {
        binding.recyclerViewLessonsQuiz.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        /*quizAdapter = AnxietyQuizLessonsAdapter(quizAnxietyDataClass)
        val questions = generateQuestions()
        binding.recyclerViewLessonsQuiz.adapter = quizAdapter*/

        val questions = generateQuestions()
        val adapter = AnxietyQuizLessonsAdapter(questions)
        binding.recyclerViewLessonsQuiz.adapter = adapter
    }

    private fun generateQuestions(): List<AnxietyLessonsQuizDataClass> {
        val questions = mutableListOf<AnxietyLessonsQuizDataClass>()
        // Inside your activity or fragment
        val myStringArray: Array<String> = resources.getStringArray(com.calmscient.R.array.anxiety_question_3_2)
        val myList: List<String> = myStringArray.toList()

        val lines = Arrays.asList(*resources.getStringArray(com.calmscient.R.array.anxiety_question_3_2))

        for (i in 1..lines.size) {
            val questionText = lines
            val options = listOf("Did not apply to me at all", "Applied to me to some degree, or some of the time",
                "Applied to me to a considerable degree, or a good part of time", "Applied to me very much, or most of the time")
            val question = AnxietyLessonsQuizDataClass(questionText, options)
            questions.add(question)
        }
        return questions
    }
}