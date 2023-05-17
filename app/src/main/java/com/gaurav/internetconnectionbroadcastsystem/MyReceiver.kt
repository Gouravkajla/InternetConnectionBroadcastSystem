package com.gaurav.internetconnectionbroadcastsystem

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telecom.ConnectionService
import android.widget.Toast


class MyReceiver():BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        connectivityReceiverListener?.onNetworkConnectionChanger(isConnectedOrConnecting(context!!))
    }

    private fun isConnectedOrConnecting(context: Context):Boolean{

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            val network = connectivityManager.activeNetwork ?: return false


            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true


                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true


                else -> false
            }
        }else {

            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    interface ConnectivityReceiverListener{
        fun onNetworkConnectionChanger(isConnected: Boolean)
    }

    companion object{
        var connectivityReceiverListener:ConnectivityReceiverListener? = null
    }

}