package ru.s4nchez.pix4bayretrospective.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.view.View

fun View.visibilityByFlag(flag: Boolean) {
    this.visibility = if (flag) View.VISIBLE else View.GONE
}

fun Context.hasPermission(permission: String) =
        this.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

