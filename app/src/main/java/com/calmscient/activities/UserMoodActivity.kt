package com.calmscient.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.calmscient.R
import com.calmscient.databinding.ActivityUserMoodBinding
import java.util.Calendar
import java.util.Date


class UserMoodActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityUserMoodBinding
    private var isImage1Visible = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserMoodBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        greeting()
        /*binding.imgMBad.setOnClickListener {
            if(isImage1Visible){
                binding.imgMBad.setImageResource(com.calmscient.R.drawable.icon_excellent)
            }
            // Update the flag to keep track of the currently visible image
            isImage1Visible = !isImage1Visible
        }*/
        //morning Clicks
        binding.imgMBad.setOnClickListener(this);
        binding.imgMBetter.setOnClickListener(this);
        binding.imgMGood.setOnClickListener(this);
        binding.imgMFair.setOnClickListener(this);
        binding.imgMExcellent.setOnClickListener(this);
        //afternoon clicks
        binding.imgEveBad.setOnClickListener(this);
        binding.imgEveBetter.setOnClickListener(this);
        binding.imgEveGood.setOnClickListener(this);
        binding.imgEveFair.setOnClickListener(this);
        binding.imgEveExcellent.setOnClickListener(this);
        //evening clicks
        binding.imgNigBad.setOnClickListener(this);
        binding.imgNigBetter.setOnClickListener(this);
        binding.imgNigFair.setOnClickListener(this);
        binding.imgNigGood.setOnClickListener(this);
        binding.imgNigExcellent.setOnClickListener(this)
        binding.imgFamily.setOnClickListener(this)
        binding.imgFriends.setOnClickListener(this)
        binding.imgWorkmates.setOnClickListener(this)
        binding.imgOthers.setOnClickListener(this)
        binding.imgAlone.setOnClickListener(this)

    }

    fun greeting() {
        val date = Date()
        val cal = Calendar.getInstance()
        cal.time = date
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
        var greeting: String? = null
        if (hour in 6..11) {
            greeting = getString(R.string.good_morning)
        } else if (hour in 12..16) {
            //greeting = getString(R.string.good_afternoon)
            greeting = getString(R.string.good_afternoon)
        } else if (hour in 17..23) {
            //greeting = getString(R.string.good_evening)
            greeting = getString(R.string.good_evening)
        } /*else if (hour in 21..23) {
                greeting = "Good Night";
            } else {
                greeting = getString(R.string.good_morning)
            }*/
        binding.idWishes.setText(greeting)
        if (greeting == getString(R.string.good_morning)) {
            binding.cardMorniMood.visibility = View.VISIBLE
            binding.mornHoursSleepCard.visibility = View.VISIBLE
            binding.idMornMeds.visibility = View.VISIBLE
            //binding.cardDailyJournel.visibility = View.VISIBLE
        } else if (greeting == getString(R.string.good_afternoon)) {
            binding.cardAfternoon.visibility = View.VISIBLE
        } else if (greeting == getString(R.string.good_evening)) {
            binding.cardEveDay.visibility = View.VISIBLE
            binding.spendTimeCard.visibility = View.VISIBLE
            binding.idMornMeds.visibility = View.VISIBLE
            binding.cardDailyJournel.visibility = View.VISIBLE
        }
    }


    override fun onClick(v: View?) {
        binding.imgMBad.setImageResource(R.drawable.icon_bad)
        binding.imgMBetter.setImageResource(R.drawable.icon_better)
        binding.imgMFair.setImageResource(R.drawable.icon_fair)
        binding.imgMGood.setImageResource(R.drawable.icon_good)
        binding.imgMExcellent.setImageResource(R.drawable.icon_excellent)


        when (v!!.id) {
            //morning images
            R.id.img_mBad -> {
                //binding.imgMBad.setImageResource(R.drawable.icon_excellent)
                //binding.imgMBad.setImageResource(R.drawable.icon_excellent)
                //binding.imgMBad.setBackgroundResource(R.drawable.drawable_circular_border)
                binding.imgMBad.setElevation(20.0F)
                binding.imgMBetter.setElevation(0.0F)
                binding.imgMFair.setElevation(0.0F)
                binding.imgMGood.setElevation(0.0F)
                binding.imgMExcellent.setElevation(0.0F)
            }

            R.id.img_mBetter -> {
                //binding.imgMBetter.setImageResource(R.drawable.icon_better)
                binding.imgMBetter.setElevation(20.0F)
                binding.imgMBad.setElevation(0.0F)
                binding.imgMFair.setElevation(0.0F)
                binding.imgMGood.setElevation(0.0F)
                binding.imgMExcellent.setElevation(0.0F)
            }

            R.id.img_mFair -> {
                //binding.imgMFair.setImageResource(R.drawable.icon_excellent)
                binding.imgMFair.setElevation(20.0F)
                binding.imgMBetter.setElevation(0.0F)
                binding.imgMBad.setElevation(0.0F)
                binding.imgMGood.setElevation(0.0F)
                binding.imgMExcellent.setElevation(0.0F)
            }

            R.id.img_mGood -> {
                //binding.imgMGood.setImageResource(R.drawable.icon_excellent)
                binding.imgMGood.setElevation(20.0F)
                binding.imgMBetter.setElevation(0.0F)
                binding.imgMBad.setElevation(0.0F)
                binding.imgMFair.setElevation(0.0F)
                binding.imgMExcellent.setElevation(0.0F)

            }

            R.id.img_mExcellent -> {
                //binding.imgMExcellent.setImageResource(R.drawable.icon_excellent)
                binding.imgMExcellent.setElevation(20.0F)
                binding.imgMGood.setElevation(0.0F)
                binding.imgMBetter.setElevation(0.0F)
                binding.imgMBad.setElevation(0.0F)
                binding.imgMFair.setElevation(0.0F)
            }

            //afternoon click
            R.id.img_eve_bad -> {
                //binding.imgMBad.setImageResource(R.drawable.icon_excellent)
                //binding.imgMBad.setImageResource(R.drawable.icon_excellent)
                //binding.imgMBad.setBackgroundResource(R.drawable.drawable_circular_border)
                binding.imgEveBad.setElevation(20.0F)
                binding.imgEveBetter.setElevation(0.0F)
                binding.imgEveFair.setElevation(0.0F)
                binding.imgEveExcellent.setElevation(0.0F)
                binding.imgEveGood.setElevation(0.0F)
            }

            R.id.img_eve_better -> {
                //binding.imgMBetter.setImageResource(R.drawable.icon_better)
                binding.imgEveBetter.setElevation(20.0F)
                binding.imgEveFair.setElevation(0.0F)
                binding.imgEveExcellent.setElevation(0.0F)
                binding.imgEveGood.setElevation(0.0F)
                binding.imgEveBad.setElevation(0.0F)

            }

            R.id.img_eve_fair -> {
                //binding.imgMFair.setImageResource(R.drawable.icon_excellent)
                binding.imgEveFair.setElevation(20.0F)
                binding.imgEveExcellent.setElevation(0.0F)
                binding.imgEveGood.setElevation(0.0F)
                binding.imgEveBetter.setElevation(0.0F)
                binding.imgEveBad.setElevation(0.0F)
            }

            R.id.img_eve_good -> {
                //binding.imgMGood.setImageResource(R.drawable.icon_excellent)
                binding.imgEveGood.setElevation(20.0F)
                binding.imgEveExcellent.setElevation(0.0F)
                binding.imgEveBetter.setElevation(0.0F)
                binding.imgEveBad.setElevation(0.0F)
                binding.imgEveFair.setElevation(0.0F)

            }

            R.id.img_eve_excellent -> {
                //binding.imgMExcellent.setImageResource(R.drawable.icon_excellent)
                binding.imgEveExcellent.setElevation(20.0F)
                binding.imgEveGood.setElevation(0.0F)
                binding.imgEveBetter.setElevation(0.0F)
                binding.imgEveBad.setElevation(0.0F)
                binding.imgEveFair.setElevation(0.0F)
            }
            //evening images
            R.id.img_nig_bad -> {
                //binding.imgMBad.setImageResource(R.drawable.icon_excellent)
                //binding.imgMBad.setImageResource(R.drawable.icon_excellent)
                //binding.imgMBad.setBackgroundResource(R.drawable.drawable_circular_border)
                binding.imgNigBad.setElevation(20.0F)
                binding.imgNigBetter.setElevation(0.0F)
                binding.imgNigFair.setElevation(0.0F)
                binding.imgNigGood.setElevation(0.0F)
                binding.imgNigExcellent.setElevation(0.0F)
            }

            R.id.img_nig_better -> {
                //binding.imgMBetter.setImageResource(R.drawable.icon_better)
                binding.imgNigBetter.setElevation(20.0F)
                binding.imgNigFair.setElevation(0.0F)
                binding.imgNigGood.setElevation(0.0F)
                binding.imgNigExcellent.setElevation(0.0F)
                binding.imgNigBad.setElevation(0.0F)

            }

            R.id.img_nig_fair -> {
                //binding.imgMFair.setImageResource(R.drawable.icon_excellent)
                binding.imgNigFair.setElevation(20.0F)
                binding.imgNigGood.setElevation(0.0F)
                binding.imgNigExcellent.setElevation(0.0F)
                binding.imgNigBad.setElevation(0.0F)
                binding.imgNigBetter.setElevation(0.0F)
            }

            R.id.img_nig_good -> {
                //binding.imgMGood.setImageResource(R.drawable.icon_excellent)
                binding.imgNigGood.setElevation(20.0F)
                binding.imgNigExcellent.setElevation(0.0F)
                binding.imgNigBad.setElevation(0.0F)
                binding.imgNigBetter.setElevation(0.0F)
                binding.imgNigFair.setElevation(0.0F)

            }

            R.id.img_nig_excellent -> {
                //binding.imgMExcellent.setImageResource(R.drawable.icon_excellent)
                binding.imgNigExcellent.setElevation(20.0F)
                binding.imgNigBad.setElevation(0.0F)
                binding.imgNigBetter.setElevation(0.0F)
                binding.imgNigFair.setElevation(0.0F)
                binding.imgNigGood.setElevation(0.0F)
            }

            R.id.img_family -> {
                binding.imgFamily.setImageResource(R.drawable.family_selected)
                binding.imgFriends.setImageResource(R.drawable.friends)
                binding.imgWorkmates.setImageResource(R.drawable.workmates)
                binding.imgOthers.setImageResource(R.drawable.others)
                binding.imgAlone.setImageResource(R.drawable.alone)
            }

            R.id.img_friends -> {
                binding.imgFriends.setImageResource(R.drawable.friends_selected)
                binding.imgFamily.setImageResource(R.drawable.family)
                binding.imgWorkmates.setImageResource(R.drawable.workmates)
                binding.imgOthers.setImageResource(R.drawable.others)
                binding.imgAlone.setImageResource(R.drawable.alone)
            }

            R.id.img_workmates -> {
                binding.imgWorkmates.setImageResource(R.drawable.workmates_selected)
                binding.imgFamily.setImageResource(R.drawable.family)
                binding.imgFriends.setImageResource(R.drawable.friends)
                binding.imgOthers.setImageResource(R.drawable.others)
                binding.imgAlone.setImageResource(R.drawable.alone)
            }

            R.id.img_others -> {
                binding.imgOthers.setImageResource(R.drawable.others_selected)
                binding.imgWorkmates.setImageResource(R.drawable.workmates)
                binding.imgFamily.setImageResource(R.drawable.family)
                binding.imgFriends.setImageResource(R.drawable.friends)
                binding.imgAlone.setImageResource(R.drawable.alone)
            }

            R.id.img_alone -> {
                binding.imgAlone.setImageResource(R.drawable.alone_selected)
                binding.imgWorkmates.setImageResource(R.drawable.workmates)
                binding.imgFamily.setImageResource(R.drawable.family)
                binding.imgFriends.setImageResource(R.drawable.friends)
                binding.imgOthers.setImageResource(R.drawable.others)
            }
        }
    }
}