package com.gaurav.internetconnectionbroadcastsystem

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gaurav.internetconnectionbroadcastsystem.databinding.ActivityMainBinding

class InternetConnectionBroadcastSystem : AppCompatActivity(),
    MyReceiver.ConnectivityReceiverListener {
    lateinit var myReceiver: MyReceiver
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView (binding.root)
        registerReceiver(MyReceiver() ,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onResume() {
        super.onResume()
        MyReceiver.connectivityReceiverListener=this

    }

    override fun onNetworkConnectionChanger(isConnected: Boolean) {
          if (isConnected){
              Toast.makeText(this, "Tuhada Internet connected Ha\nEnjoy!!" , Toast.LENGTH_SHORT).show()
          }
          else{
              Toast.makeText(this, "Appna Internet Off ha\nKirpa karke ON karo ji", Toast.LENGTH_SHORT).show()
          }
    }

    override fun onStop() {
        super.onStop()
        MyReceiver.connectivityReceiverListener= this
    }
}