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
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.Interface.CellClickListener
import com.calmscient.R
import com.calmscient.adapters.MedicationsCardAdapter
import com.calmscient.adapters.ScreeningsCardAdapter
import com.calmscient.adapters.YourStressTriggersQuizAdapter
import com.calmscient.databinding.CalendarFragmentLayoutBinding
import com.calmscient.databinding.FragmentScreeningsBinding
import com.calmscient.databinding.FragmentYourStressTriggersQuizBinding
import com.calmscient.di.remote.StressTriggerDataClass


class YourStressTriggersQuizFragment : Fragment() {
    private lateinit var cardViewAdapter: YourStressTriggersQuizAdapter
    private val cardViewItems = mutableListOf<StressTriggerDataClass>()

    private lateinit var binding: FragmentYourStressTriggersQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            loadFragment(ChangingYourResponseFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYourStressTriggersQuizBinding.inflate(inflater, container, false)
        binding.backIcon.setOnClickListener {
            loadFragment(ChangingYourResponseFragment())
        }

        // Check if arguments are provided
        val description = arguments?.getString("description")
        description?.let {
            binding.tvTite.text = it
        }
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.stressTriggersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cardViewAdapter = YourStressTriggersQuizAdapter(cardViewItems)
        binding.stressTriggersRecyclerView.adapter = cardViewAdapter
        displayCardViews()
    }


    private fun displayCardViews() {
        cardViewItems.clear()
        cardViewItems.addAll(
            listOf(
                StressTriggerDataClass("Work-related stress quiz",  R.drawable.ic_next_new),
                StressTriggerDataClass("Personal health stress quiz", R.drawable.ic_next_new),
                StressTriggerDataClass("Family-related stress quiz",  R.drawable.ic_next_new),
                StressTriggerDataClass("Social/relationship stress quiz", R.drawable.ic_next_new),


            )
        )
        cardViewAdapter.notifyDataSetChanged()
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



}