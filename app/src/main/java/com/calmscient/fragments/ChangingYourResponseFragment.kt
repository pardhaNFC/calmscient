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
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.AnxietyIntroductionAdapter
import com.calmscient.adapters.CardItemDiffCallback1
import com.calmscient.adapters.ChangingYourResponseAdapter
import com.calmscient.di.remote.CardItemDataClass
import com.calmscient.di.remote.ItemType
import com.calmscient.databinding.FragmentChangingYourResponseBinding
import com.calmscient.utils.common.SavePreferences

class ChangingYourResponseFragment : Fragment() {
    private lateinit var savePrefData: SavePreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChangingYourResponseBinding.inflate(inflater, container, false)
        savePrefData = SavePreferences(requireContext())

        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )




        // Data for Understanding the stress signs
        val stressSignsItems = cardItemsStressSigns()
        val stressSignsRecyclerView: RecyclerView = binding.recyclerViewUnderstandingStressSigns
        val stressSignsAdapter = ChangingYourResponseAdapter(CardItemDiffCallback1())
        setupRecyclerView(stressSignsRecyclerView, stressSignsItems, stressSignsAdapter)

        // Data for Understanding the cause of your stress
        val yourStressItems = cardItemsYourStress()
        val yourStressRecyclerView: RecyclerView = binding.recyclerViewUnderstandingCauseOfYourStress
        val yourStressAdapter = ChangingYourResponseAdapter(CardItemDiffCallback1())
        setupRecyclerView(yourStressRecyclerView, yourStressItems, yourStressAdapter)

         // Data for Understanding your stress response
         val stressResponseItems = cardItemsStressResponse()
         val stressResponseRecyclerView: RecyclerView = binding.recyclerViewUnderstandingStressResponse
         val stressResponseAdapter = ChangingYourResponseAdapter(CardItemDiffCallback1())
         setupRecyclerView(stressResponseRecyclerView, stressResponseItems, stressResponseAdapter)

        // Data for Resources
        val resourceItems = cardItemsResource()
        val resourceRecyclerView: RecyclerView = binding.recyclerViewResources
        val resourceAdapter = ChangingYourResponseAdapter(CardItemDiffCallback1())
        setupRecyclerView(resourceRecyclerView, resourceItems, resourceAdapter)



        binding.icGlossary.setOnClickListener {
            //startActivity(Intent(requireContext(), GlossaryActivity::class.java))
            loadFragment(GlossaryFragment())

        }
        binding.backIcon.setOnClickListener {
            //requireActivity().onBackPressed()
            loadFragment(DiscoveryFragment())
        }

        return binding.root
    }


    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        cardItems: List<CardItemDataClass>,
        adapter: ChangingYourResponseAdapter
    ) {
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        adapter.submitList(cardItems)
    }

    private fun cardItemsStressSigns(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.VIDEO),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_signs_1),
            description = getString(R.string.where_can_stress_hide),
            isCompleted = true,
            heading = null,
            summary = null,
            dialogText = null
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.AUDIO),
            audioResourceId = null, // Replace with actual audio resource ID
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_signs_2),
            description = getString(R.string.meet_nora_austin),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card3 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.QUIZ),
            audioResourceId = null, // Replace with actual audio resource ID
            videoResourceId = null,
            contentIcons = listOf(R.drawable.changing_response_quiz),
            description = getString(R.string.your_physiological_signs_of_stress_quiz),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card4 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null, // Replace with actual audio resource ID
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_signs_4),
            description = getString(R.string.what_are_physiological_signs_of_stress),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card5 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.QUIZ),
            audioResourceId = null, // Replace with actual audio resource ID
            videoResourceId = null,
            contentIcons = listOf(R.drawable.changing_response_quiz),
            description = getString(R.string.your_emotional_signs_of_stress_quiz),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card6 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null, // Replace with actual audio resource ID
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_signs_6),
            description = getString(R.string.emotional_signs_of_stress_fight_flight_freeze_shutdown),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card7 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null, // Replace with actual audio resource ID
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_signs_7),
            description = getString(R.string.window_of_tolerance),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card8 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.QUIZ),
            audioResourceId = null, // Replace with actual audio resource ID
            videoResourceId = null,
            contentIcons = listOf(R.drawable.changing_response_quiz),
            description = getString(R.string.your_behavioral_signs_of_stress_quiz),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card9 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null, // Replace with actual audio resource ID
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_signs_9),
            description = getString(R.string.defense_response_and_behavioral_response),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        // Add more CardItemDataClass instances as needed for section 1
        return listOf(card1, card2, card3,card4,card5,card6,card7,card8,card9)
    }

    private fun cardItemsYourStress(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.VIDEO),
            audioResourceId = null,
            videoResourceId = "https://calmscient-videos.s3.ap-south-1.amazonaws.com/L1-1-Neuropsychology+of+Anxiety+(1).mp4",
            contentIcons = listOf(R.drawable.cause_your_stress_1),
            description = getString(R.string.what_is_causing_you_stress),
            isCompleted = false,
            heading = getString(R.string.what_is_causing_you_stress),
            summary = getString(R.string.lesson1_video_summary),
            dialogText = getString(R.string.lesson1_video1_description),
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.AUDIO),
            audioResourceId = "https://calmscient-videos.s3.ap-south-1.amazonaws.com/Lesson+1-2+Meet+Nora%2C+Austin+and+Melanie.wav",
            videoResourceId = null,
            contentIcons = listOf(R.drawable.cause_your_stress_2),
            description = getString(R.string.what_s_causing_nora_austin_and_melanie_stress),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )

        val card3 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = "https://calmscient-videos.s3.ap-south-1.amazonaws.com/Lesson+1-2+Meet+Nora%2C+Austin+and+Melanie.wav",
            videoResourceId = null,
            contentIcons = listOf(R.drawable.cause_your_stress_3),
            description = getString(R.string.what_triggers_our_stress),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card4 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.QUIZ),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.changing_response_quiz),
            description = getString(R.string.your_stress_triggers_quiz),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        // Add more CardItemDataClass instances as needed for cardItemsYourStress
        return listOf(card1, card2, card3, card4)
    }

    private fun cardItemsStressResponse(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.VIDEO),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_response_1),
            description = getString(R.string.what_is_your_response_to_stress),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_response_2),
            description = getString(R.string.push_away_avoidance),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card3 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_response_3),
            description = getString(R.string.push_through),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card4 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_response_4),
            description = getString(R.string.stress_hormones),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card5 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.stress_response_5),
            description = getString(R.string.in_denial),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )

        // Add more CardItemDataClass instances as needed for
        return listOf(card1, card2, card3, card4, card5)
    }

    private fun cardItemsResource(): List<CardItemDataClass> {
        val card1 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.resource_1),
            description = getString(R.string.coping_with_stress),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )

        val card2 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.resource_2),
            description = getString(R.string.cost_benefit_analysis),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )

        val card3 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.resource_3),
            description = getString(R.string.relaxation_techniques),
            isCompleted = false,
            heading = null,
            summary = null,
            dialogText = null
        )
        val card4 = CardItemDataClass(
            availableContentTypes = listOf(ItemType.LESSON),
            audioResourceId = null,
            videoResourceId = null,
            contentIcons = listOf(R.drawable.resource_4),
            description = getString(R.string.biased_thinking_and_stress),
            isCompleted = false,
            heading =null,
            summary = null,
            dialogText = null
        )

        // Add more CardItemDataClass instances as needed for
        return listOf(card1, card2, card3, card4)
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
