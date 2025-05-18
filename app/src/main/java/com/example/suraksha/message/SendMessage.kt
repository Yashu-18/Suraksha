package com.example.suraksha.message

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast


class SendMessage(val context: Context) {

    private var locationListener: LocationListener? = null

    private fun getLocation(phNumberList: List<String>) {
        Log.d("getLocation", "Function getting called!!")
        // Code for getting location & sending sms message
        locationListener =
            LocationListener { location ->
                try {
                    Log.d("LatLong", "Reached LatLong")
                    val myLatitude = location.latitude.toString()
                    val myLongitude = location.longitude.toString()
                    val message =
                        """
                      I need help, please try and reach out to me ASAP. Below is my location:
                      http://maps.google.com/?q=$myLatitude,$myLongitude
                      """.trimIndent()


                    val smsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        context.getSystemService(SmsManager::class.java)
                    } else {
                        SmsManager.getDefault()
                    }

                    for (phNum in phNumberList) {
                        smsManager.sendTextMessage(phNum, null, message, null, null)
                        Log.d("YperSMS", "sentt")
                    }
                    stopLocationUpdates()
                    Toast.makeText(context, "Messages Sent!", Toast.LENGTH_SHORT).show()

                } catch (e: Exception) {
                    Log.d("LatLong", "LatLong not found")
                    e.printStackTrace()
                }
            }

        // Requesting location
        val locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        try {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                100,
                0f,
                locationListener!!
            )
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                100,
                0f,
                locationListener!!
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun onBtnClick(phNumberList: List<String>) {
        if (phNumberList.isEmpty()) {
            return
        }
        getLocation(phNumberList)
    }

    private fun stopLocationUpdates() {
        val locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener?.let {
            locationManager.removeUpdates(it)
            locationListener = null
        }
    }
}