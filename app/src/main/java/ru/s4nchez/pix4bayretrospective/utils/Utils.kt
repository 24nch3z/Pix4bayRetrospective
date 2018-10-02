package ru.s4nchez.pix4bayretrospective.utils

import android.content.Context
import android.net.ConnectivityManager

fun isInternetConnected(context: Context): Boolean {
    val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    if (activeNetwork == null) return false
    val isConnected = activeNetwork.isConnected
    return isConnected
}