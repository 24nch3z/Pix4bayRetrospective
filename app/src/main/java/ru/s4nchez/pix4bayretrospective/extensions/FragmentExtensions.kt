package ru.s4nchez.pix4bayretrospective.extensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.toastRes(@StringRes res: Int) {
    if (this.context != null) Toast.makeText(this.context, res, Toast.LENGTH_SHORT).show()
}

fun Fragment.requestPermission(permission: String, requestCode: Int) {
    this.activity?.requestPermission(permission, requestCode)
}

fun Fragment.hasPermission(permission: String) = this.context?.hasPermission(permission) ?: false

//fun Fragment.toast(s: String, duration: Int) {
////    if (this.context != null) Toast.makeText(this.context, s, duration).show()
//    if (this.context != null) MyToast.get(this.context).show(s)
//}