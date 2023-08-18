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
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.adapters.GlossaryAdapter
import com.calmscient.data.remote.Task
import com.calmscient.databinding.LayoutGlossaryBinding

class GlossaryActivity : AppCompatActivity() {
    lateinit var binding:LayoutGlossaryBinding
    private val glossaryAdapter = GlossaryAdapter(mutableListOf())
    private val tasks = listOf(
        Task("A","Clean kitchen", "Sweep floor, take out trash, clean dishes."),
        Task("B","Write blog post", "Prepare code and outline, cry, write, cry again, hit publish."),
        Task("C","Finish app", "Ain't happening today son."))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutGlossaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
    override fun onStart()
    {
        binding.glossaryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.glossaryRecyclerView.adapter = glossaryAdapter
        glossaryAdapter.updateTasks(tasks)
        super.onStart()
    }
}