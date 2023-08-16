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
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.Interface.CellClickListener
import com.calmscient.R
import com.calmscient.adapters.MedicationsCardAdapter
import com.calmscient.adapters.ScreeningsCardAdapter
import com.calmscient.databinding.CalendarFragmentLayoutBinding
import com.calmscient.databinding.FragmentScreeningsBinding

data class ScreeningsCardItem(
    val title: String,
    val historyImageResource: Int?,
    val nextOrKeyImageResource: Int?,
    )
class ScreeningsFragment : Fragment(), CellClickListener {
    private lateinit var cardViewAdapter: ScreeningsCardAdapter
    private val cardViewItems = mutableListOf<ScreeningsCardItem>()

    private lateinit var binding: FragmentScreeningsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreeningsBinding.inflate(inflater, container, false)
        binding.backIcon.setOnClickListener {
            loadFragment(MedicalRecordsFragment())
        }

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewMedications.layoutManager = LinearLayoutManager(requireContext())
        cardViewAdapter = ScreeningsCardAdapter(cardViewItems)
        binding.recyclerViewMedications.adapter = cardViewAdapter
        displayCardViews()
    }


    private fun displayCardViews() {
        cardViewItems.clear()
        cardViewItems.addAll(
            listOf(
                ScreeningsCardItem("PHQ-9", R.drawable.ic_history, R.drawable.ic_next),
                ScreeningsCardItem("GAD-7", null, R.drawable.ic_next),
                ScreeningsCardItem("AUDIT", null, R.drawable.ic_next),
                ScreeningsCardItem("DAST-10", null, R.drawable.ic_next),
                ScreeningsCardItem("SBIRT", null, R.drawable.ic_key)

            )
        )
        cardViewAdapter.notifyDataSetChanged()
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.commit()
    }

    override fun onCellClickListener(position: Int) {

    }
}