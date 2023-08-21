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
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.AnxietyIntroductionAdapter
import com.calmscient.adapters.CardItemDiffCallback
import com.calmscient.data.remote.CardItemDataClass
import com.calmscient.data.remote.ItemType

import com.calmscient.databinding.ActivityManageAnxietyBinding
import com.calmscient.fragments.DiscoveryFragment

class ManageAnxietyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityManageAnxietyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        // Data for Introduction
        val introductionItems = cardItemsIntroduction()
        val introductionRecyclerView: RecyclerView = binding.recyclerViewIntroduction
        val introductionAdapter = AnxietyIntroductionAdapter(CardItemDiffCallback())
        setupRecyclerView(introductionRecyclerView, introductionItems, introductionAdapter)

        // Data for lesson 1
        val lesson1Items = cardItemsLesson1()
        val lesson1RecyclerView: RecyclerView = binding.recyclerViewLesson1
        val lesson1Adapter = AnxietyIntroductionAdapter(CardItemDiffCallback())
        setupRecyclerView(lesson1RecyclerView, lesson1Items, lesson1Adapter)

        // Data for lesson 2
        val lesson2Items = cardItemsLesson2()
        val lesson2RecyclerView: RecyclerView = binding.recyclerViewLesson2
        val lesson2Adapter = AnxietyIntroductionAdapter(CardItemDiffCallback())
        setupRecyclerView(lesson2RecyclerView, lesson2Items, lesson2Adapter)

        // Data for lesson 3
        val lesson3Items = cardItemsLesson3()
        val lesson3RecyclerView: RecyclerView = binding.recyclerViewLesson3
        val lesson3Adapter = AnxietyIntroductionAdapter(CardItemDiffCallback())
        setupRecyclerView(lesson3RecyclerView, lesson3Items, lesson3Adapter)

        // Data for lesson 4
        val lesson4Items = cardItemsLesson4()
        val lesson4RecyclerView: RecyclerView = binding.recyclerViewLesson4
        val lesson4Adapter = AnxietyIntroductionAdapter(CardItemDiffCallback())
        setupRecyclerView(lesson4RecyclerView, lesson4Items, lesson4Adapter)

        // Data for lesson 5
        val lesson5Items = cardItemsLesson5()
        val lesson5RecyclerView: RecyclerView = binding.recyclerViewLesson5
        val lesson5Adapter = AnxietyIntroductionAdapter(CardItemDiffCallback())
        setupRecyclerView(lesson5RecyclerView, lesson5Items, lesson5Adapter)

        // Data for lesson 6
        val lesson6Items = cardItemsLesson6()
        val lesson6RecyclerView: RecyclerView = binding.recyclerViewLesson6
        val lesson6Adapter = AnxietyIntroductionAdapter(CardItemDiffCallback())
        setupRecyclerView(lesson6RecyclerView, lesson6Items, lesson6Adapter)

        // Data for Additional Resource
        val additionalResourceItems = cardItemsAdditionalResource()
        val additionalResourceRecyclerView: RecyclerView = binding.recyclerViewAdditionalResource
        val additionalResourceAdapter = AnxietyIntroductionAdapter(CardItemDiffCallback())
        setupRecyclerView(
            additionalResourceRecyclerView, additionalResourceItems, additionalResourceAdapter
        )

        binding.icGlossary.setOnClickListener {
            startActivity(Intent(this, GlossaryActivity::class.java))
        }
        binding.backIcon.setOnClickListener {
            startActivity(Intent(this, BeginManageAnxietyActivity::class.java))
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Toast.makeText(requireContext(), "Back Button is calling", Toast.LENGTH_SHORT).show()
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.commit()
    }

    private fun cardItemsIntroduction(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.intro1),
            description = "What is anxiety?",
            isCompleted = true
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null, // Replace with actual audio resource ID
            videoResourceId = null,
            contentIcons = listOf(R.drawable.intro2),
            description = "Get yourself out of “fast pace cycle”",
            isCompleted = false
        )
        val card3 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null, // Replace with actual audio resource ID
            videoResourceId = null,
            contentIcons = listOf(R.drawable.intro3),
            description = "Let’s make a plan",
            isCompleted = false
        )

        // Add more CardItemDataClass instances as needed for section 1
        return listOf(card1, card2, card3)
    }

    private fun cardItemsLesson1(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.VIDEO),
            audioResourceId = null,
            videoResourceId = R.raw.video3,
            contentIcons = listOf(R.drawable.video),
            description = "Neuropsychology of anxiety",
            isCompleted = false
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.AUDIO),
            audioResourceId = R.raw.audio2,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.audio),
            description = null,
            isCompleted = false
        )
        // Add more CardItemDataClass instances as needed for Lesson1
        return listOf(card1, card2)
    }

    private fun cardItemsLesson2(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.lesson2_layer1),
            description = "Recognize a cycle of anxiety",
            isCompleted = false
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.QUIZ),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.quiz),
            description = "Quiz",
            isCompleted = false
        )

        // Add more CardItemDataClass instances as needed for lesson 2
        return listOf(card1, card2)
    }

    private fun cardItemsLesson3(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.lesson3_layer1),
            description = "Where can anxiety hide?",
            isCompleted = false
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.QUIZ),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.quiz),
            description = "Quiz",
            isCompleted = false
        )

        val card3 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.AUDIO),
            audioResourceId = R.raw.audio1,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.audio),
            description = null,
            isCompleted = false
        )

        val card4 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.QUIZ),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.quiz),
            description = "Quiz",
            isCompleted = false
        )

        // Add more CardItemDataClass instances as needed for lesson3
        return listOf(card1, card2, card3, card4)
    }

    private fun cardItemsLesson4(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.VIDEO),
            audioResourceId = null,
            videoResourceId = R.raw.video3,
            contentIcons = listOf(R.drawable.video),
            description = "Implement body calming skills",
            isCompleted = false
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.lesson4_layer1),
            description = "Calming the body",
            isCompleted = false
        )

        // Add more CardItemDataClass instances as needed for lesson4
        return listOf(card1, card2)
    }

    private fun cardItemsLesson5(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.AUDIO),
            audioResourceId = R.raw.audio2,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.audio),
            description = "Anxiety and worry",
            isCompleted = false
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.lesson5_layer1),
            description = "Learn to postpone your worry",
            isCompleted = false
        )

        // Add more CardItemDataClass instances as needed for lesson5
        return listOf(card1, card2)
    }

    private fun cardItemsLesson6(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.AUDIO),
            audioResourceId = R.raw.audio1,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.audio),
            description = "Calming your anxious mind",
            isCompleted = false
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.lesson6_layer1),
            description = "Biased thinking",
            isCompleted = false
        )

        val card3 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.AUDIO),
            audioResourceId = R.raw.audio2,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.audio),
            description = "Making connection",
            isCompleted = false
        )

        val card4 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.lesson6_layer2),
            description = "Restructure biased thinking",
            isCompleted = false
        )

        // Add more CardItemDataClass instances as needed for lesson6
        return listOf(card1, card2, card3, card4)
    }

    private fun cardItemsAdditionalResource(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.ar1),
            description = "Anxiety and Exercise",
            isCompleted = false
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.ar2),
            description = "Anxiety and Sleep",
            isCompleted = false
        )

        val card3 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.ar3),
            description = "Anxiety and Alcohol/Substances",
            isCompleted = false
        )

        val card4 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.ar4),
            description = "Managing stress to reduce anxiety",
            isCompleted = false
        )

        // Add more CardItemDataClass instances as needed for Additional Resource
        return listOf(card1, card2, card3, card4)
    }


    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        cardItems: List<CardItemDataClass>,
        adapter: AnxietyIntroductionAdapter
    ) {
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        adapter.submitList(cardItems)
    }

}