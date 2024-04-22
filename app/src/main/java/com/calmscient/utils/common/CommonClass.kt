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

package com.calmscient.utils.common

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.calmscient.R

object CommonClass {

    fun isNetworkAvailable(context: Context): Boolean {
        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false
            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    fun showInternetDialogue(context: Context) {
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(context)
        builder.setTitle("No internet connection")
        builder.setMessage("We're having trouble reaching the network. Check your connection or try again.")
        builder.setPositiveButton(
            R.string.settings,
            DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()
                val intent = Intent(Settings.ACTION_SETTINGS)
                context.startActivity(intent)
            })
        val dialog = builder.create()
        dialog.setCancelable(true)
        dialog.show()
    }

    /*fun showAlertDialogOK(context: Context, message: String): Dialog? {
        val dialog = Dialog(context)
        try {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.custom_alert)
            *//* dialog.window!!.setLayout(
                 getWidth(context) - 100,
                 LinearLayout.LayoutParams.WRAP_CONTENT
             )*//*
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val linearLayoutYesNo =
                dialog.findViewById<View>(R.id.linearLayout_CustomAlert_YesNo) as LinearLayout
            val linearLayoutOK =
                dialog.findViewById<View>(R.id.linearLayout_CustomAlert_OK) as LinearLayout
            val txtCustomAlert_Message =
                dialog.findViewById<View>(R.id.txt_CustomAlert_Message) as TextView
            val txtCustomAlert_Yes = dialog.findViewById<View>(R.id.txt_CustomAlert_Yes) as TextView
            val txtCustomAlert_No = dialog.findViewById<View>(R.id.txt_CustomAlert_No) as TextView
            val txtCustomAlert_OK = dialog.findViewById<View>(R.id.txt_CustomAlert_OK) as TextView
            if (isEmptyString(message) || message == "null") {
                txtCustomAlert_Message.setText(R.string.strAlertMessage_Something)
            } else {
                txtCustomAlert_Message.text = message
            }
            linearLayoutYesNo.visibility = View.GONE
            linearLayoutOK.visibility = View.VISIBLE
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
            txtCustomAlert_OK.setOnClickListener { dialog.dismiss() }
        } catch (exception: java.lang.Exception) {
            exception.printStackTrace()
        }
        return dialog
    }*/
}