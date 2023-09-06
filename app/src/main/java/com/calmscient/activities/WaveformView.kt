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

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.calmscient.R
import kotlin.random.Random

@SuppressLint("ResourceAsColor")
class WaveformView (context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val playedPaint = Paint()
    private val remainingPaint = Paint()
    private var waveformProgress = 0f // Current progress of audio playback (0 to 1)
    private var numBars = 100 // Number of curved bars in the waveform
    private var barWidth = 0f
    private var barGap = 0f
    private var barHeights: List<Float> = emptyList()

    init {
        playedPaint.color = Color.parseColor("#F48383") // Color for the played bars
        remainingPaint.color = Color.parseColor("#E7E7E7") // Color for the remaining bars
    }

    fun setWaveformProgress(progress: Float) {
        waveformProgress = progress
        invalidate() // Redraw the view
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        barWidth = w.toFloat() / numBars.toFloat()
        barGap = barWidth / 5f // Adjust the gap width
        generateRandomBarHeights()
    }

    private fun generateRandomBarHeights() {
        barHeights = List(numBars) { Random.nextFloat() * (height / 2f) } // Varying bar heights
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val height = canvas.height

        // Calculate the number of played and remaining bars
        val playedBars = (numBars * waveformProgress).toInt()
        val remainingBars = numBars - playedBars

        // Draw the played bars with varying heights
        for (i in 0 until playedBars) {
            val left = i * (barWidth + barGap)
            val right = left + barWidth
            val top = height / 2f - barHeights[i]
            val bottom = height / 2f + barHeights[i]
            canvas.drawRoundRect(RectF(left, top, right, bottom), barWidth / 2, barWidth / 2, playedPaint)
        }

        // Draw the remaining bars with varying heights
        for (i in playedBars until numBars) {
            val left = i * (barWidth + barGap)
            val right = left + barWidth
            val top = height / 2f - barHeights[i]
            val bottom = height / 2f + barHeights[i]
            canvas.drawRoundRect(RectF(left, top, right, bottom), barWidth / 2, barWidth / 2, remainingPaint)
        }
    }
}
