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

package com.calmscient.adapters
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.fragments.SummaryDataClasss
class TakingControlSummaryCardAdapter (private val items: List<SummaryDataClasss>) :
    RecyclerView.Adapter<TakingControlSummaryCardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.summary_takingconotrol_items, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(
        holder: TakingControlSummaryCardAdapter.CardViewHolder, position: Int) {
        val item = items[position]
        item.imageView1?.let { holder.imageView.setImageResource(it) }
        holder.textview1.text = item.text1
        holder.progressValue.progress = 10
        holder.textview2.text = item.text2
        holder.textview3.text = item.text3
    }
    private fun resultPercent() {

        val progressBar: ProgressBar
        val textView: TextView
        var progressStatus = 0
        val handler = Handler()

        Thread {
            while (progressStatus < 60) {
                progressStatus += 1
                // Update the progress bar and display the
                //current value in the text view
                handler.post(Runnable {
                    //binding.progressbarResult.progress = progressStatus
                })
                try {
                    // Sleep for 200 milliseconds.
                    Thread.sleep(25)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.imageView1)
        val textview1: TextView = itemView.findViewById(R.id.text1)
        val progressValue: ProgressBar = itemView.findViewById(R.id.progressbar_history)
        val textview2: TextView = itemView.findViewById(R.id.text2)
        val textview3: TextView = itemView.findViewById(R.id.text3)
    }
}