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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.Interface.CellClickListener
import com.calmscient.R
import com.calmscient.fragments.AppointmentdetailsFragment

import com.calmscient.fragments.CardViewItems
import com.calmscient.fragments.NextAppointmentsFragment
class NextAppointmentsAdapter ( private val items:  List<CardViewItems>):
    RecyclerView.Adapter<NextAppointmentsAdapter.CardViewHolder>() {
    lateinit var mListener: CellClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextAppointmentsAdapter.CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nextappointemts_item, parent, false)
        return CardViewHolder(view)
    }
    override fun onBindViewHolder(
        holder: NextAppointmentsAdapter.CardViewHolder, position: Int) {
        val item = items[position]
        holder.dateview1.text = item.dateview1
        item.doctor_logo?.let { holder.doctor_logo.setImageResource(it) }
        holder.DoctorNameTextView.text = item.DoctorNameTextView
        holder.HosptailNameTextView.text = item.HosptailNameTextView
        item.img_arrow1?.let{ holder.img_arrow1.setImageResource(it) }
        holder.dateview2.text = item.dateview2
        item.appointments_logo1?.let { holder.appointments_logo1.setImageResource(it) }
        holder.AppointmentsNameTextView1.text = item.AppointmentsNameTextView1
        holder.dateview3.text = item.dateview3
        item.appointments_logo2?.let { holder.appointments_logo2.setImageResource(it) }
        holder.AppointmentsNameTextView2.text = item.AppointmentsNameTextView2
        holder.dateview4.text = item.dateview4
        item.appointments_logo3?.let { holder.appointments_logo3.setImageResource(it) }
        holder.AppointmentsNameTextView3.text = item.AppointmentsNameTextView3
        holder.dateview5.text = item.dateview5
        item.appointments_logo4?.let { holder.appointments_logo4.setImageResource(it) }
        holder.AppointmentsNameTextView4.text = item.AppointmentsNameTextView4
        holder.nextAppointmentsCard.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                val fragment = AppointmentdetailsFragment()
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.flFragment, fragment).addToBackStack(null).commit()
            }
        })
    }
    override fun getItemCount(): Int = items.size
    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateview1: TextView = itemView.findViewById(R.id.dateview1)
        val nextAppointmentsCard: CardView  = itemView.findViewById(R.id.mainCard)
        val doctor_logo: ImageView = itemView.findViewById(R.id.doctor_logo)
        val DoctorNameTextView: TextView = itemView.findViewById(R.id.DoctorNameTextView)
        val HosptailNameTextView: TextView = itemView.findViewById(R.id.HosptailNameTextView)
        val img_arrow1: ImageView = itemView.findViewById(R.id.img_arrow1)
        val dateview2: TextView = itemView.findViewById(R.id.dateview2)
        val appointments_logo1: ImageView = itemView.findViewById(R.id.appointments_logo1)
        val AppointmentsNameTextView1: TextView = itemView.findViewById(R.id.AppointmentsNameTextView1)
        val dateview3: TextView = itemView.findViewById(R.id.dateview3)
        val appointments_logo2: ImageView = itemView.findViewById(R.id.appointments_logo2)
        val AppointmentsNameTextView2: TextView = itemView.findViewById(R.id.AppointmentsNameTextView2)
        val dateview4: TextView = itemView.findViewById(R.id.dateview4)
        val appointments_logo3: ImageView = itemView.findViewById(R.id.appointments_logo3)
        val AppointmentsNameTextView3: TextView = itemView.findViewById(R.id.AppointmentsNameTextView3)
        val dateview5: TextView = itemView.findViewById(R.id.dateview5)
        val appointments_logo4: ImageView = itemView.findViewById(R.id.appointments_logo4)
        val AppointmentsNameTextView4: TextView = itemView.findViewById(R.id.AppointmentsNameTextView4)
    }
}