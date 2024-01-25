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

import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.R
import com.calmscient.adapters.RestructureReviewAdapter
import com.calmscient.data.remote.ReviewItemDataClass
import com.calmscient.databinding.LayoutRestructureReviewBinding

class ReviewTypeRestructureActivity:AppCompat() {
    lateinit var binding:LayoutRestructureReviewBinding
    val items = listOf(
        ReviewItemDataClass("All or nothing (black and white) thinking"), ReviewItemDataClass("Catastrophizing"),
        ReviewItemDataClass("Perfectionist thinking"), ReviewItemDataClass("Negative (mental) filtering"),
        ReviewItemDataClass("Personalization and blame"), ReviewItemDataClass("Jumping to conclusions"),
        ReviewItemDataClass("Making negative assumptions"), ReviewItemDataClass("Emotional reasoning (“fear talk”)"),
    )
    private var reviewAdapter = RestructureReviewAdapter(this, items)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutRestructureReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        initializeAdapter()
        binding.backIcon.setOnClickListener {
            onBackPressed()
        }
    }
    private fun initializeAdapter() {
        binding.recyclerViewReviewTypes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        reviewAdapter = RestructureReviewAdapter(this, items)
        binding.recyclerViewReviewTypes.adapter = reviewAdapter
    }


}