package ru.s4nchez.pix4bayretrospective.ui.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*
import ru.s4nchez.pix4bayretrospective.R
import ru.s4nchez.pix4bayretrospective.utils.isInternetConnected
import ru.s4nchez.pix4bayretrospective.utils.visibilityByFlag

abstract class BaseActivity : AppCompatActivity() {

    private val networkStatusChangeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            internet_disable.visibilityByFlag(!isInternetConnected(context))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun setFragment(fragment: Fragment, addToBackStackFlag: Boolean) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        if (addToBackStackFlag) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(networkStatusChangeReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkStatusChangeReceiver)
    }
}
