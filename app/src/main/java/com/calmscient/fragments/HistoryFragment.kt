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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.R
import com.calmscient.adapters.HistoryCardAdapter
import com.calmscient.databinding.LayoutHistoryBinding

data class HistoryDataClass(
    val date: String,
    val time: String?,
    val progressBarValue:Int?,
    val questionCount: Int?,
)
class HistoryFragment:Fragment() {
    lateinit var binding:LayoutHistoryBinding
    lateinit var historyViewAdapter:HistoryCardAdapter
    private val historyItems = mutableListOf<HistoryDataClass>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutHistoryBinding.inflate(inflater,container,false)
        binding.historyBackIcon.setOnClickListener {
            loadFragment(ScreeningsFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerHistory.layoutManager = LinearLayoutManager(requireContext())
        historyViewAdapter = HistoryCardAdapter(historyItems)
        binding.recyclerHistory.adapter = historyViewAdapter
        displayCardViews()
    }
    private fun displayCardViews() {
        historyItems.clear()
        historyItems.addAll(
            listOf(
                HistoryDataClass("11/08/2023","03:24PM", 60, 6/10),
                HistoryDataClass("11/08/2023","03:24PM", 60, 6/10),
                HistoryDataClass("11/08/2023","03:24PM", 60, 6/10),
                HistoryDataClass("11/08/2023","03:24PM", 60, 6/10),
            )
        )
        historyViewAdapter.notifyDataSetChanged()
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.commit()
    }
}