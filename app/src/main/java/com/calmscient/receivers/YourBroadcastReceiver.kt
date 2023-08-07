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

package com.calmscient.receivers
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast

class YourBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {
            // Device has been rebooted, re-schedule your alarms here if needed
            // ...
        } else {
            // Alarm has been triggered, handle it here
            val scheduleType = intent?.getStringExtra("SCHEDULE_TYPE")
            Toast.makeText(context, "Time to take your medication for $scheduleType", Toast.LENGTH_LONG).show()

            // Vibrate the device
            val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val vibrationEffect = VibrationEffect.createWaveform(
                    longArrayOf(100, 200, 300, 400, 500),
                    intArrayOf(VibrationEffect.DEFAULT_AMPLITUDE, VibrationEffect.DEFAULT_AMPLITUDE,
                        VibrationEffect.DEFAULT_AMPLITUDE, VibrationEffect.DEFAULT_AMPLITUDE,
                        VibrationEffect.DEFAULT_AMPLITUDE),
                    -1
                )
                vibrator.vibrate(vibrationEffect)
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(VIBRATION_DURATION)
            }

            // Play the default notification sound
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone: Ringtone = RingtoneManager.getRingtone(context, notification)
            ringtone.play()

            // Acquire a wake lock to wake up the device and display the notification
            val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            val wakeLock = powerManager.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP,
                "YourApp:WakeLock"
            )
            wakeLock.acquire(2000) // Acquire for 2 seconds to show the notification

            // Show the notification
            // You can use the code from the previous message to show the notification

            // Reschedule the alarm if needed
            // ...

        }
    }

    companion object {
        private const val VIBRATION_DURATION: Long = 10000 // Vibrate for 1 second
    }
}
