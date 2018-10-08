package ru.s4nchez.pix4bayretrospective.extensions

import android.app.Activity
import android.os.Build

fun Activity.requestPermission(permission: String, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val p: Array<String> = arrayOf(permission)
        this.requestPermissions(p, requestCode)
    }
}