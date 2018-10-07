package ru.s4nchez.pix4bayretrospective.extensions

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.widget.Toast
import ru.s4nchez.pix4bayretrospective.R

fun Fragment.toastRes(@StringRes res: Int) {
    if (this.context != null) Toast.makeText(this.context, res, Toast.LENGTH_SHORT).show()
}

fun Fragment.hasPermission(permission: String) = this.context?.hasPermission(permission) ?: false

//fun Fragment.toast(s: String, duration: Int) {
////    if (this.context != null) Toast.makeText(this.context, s, duration).show()
//    if (this.context != null) MyToast.get(this.context).show(s)
//}

fun Fragment.showPermissionSnackbar(@StringRes message: Int, requestCode: Int) {
    if (activity == null) return

    Snackbar.make(activity!!.findViewById(R.id.container), message, Snackbar.LENGTH_LONG)
            .setAction(R.string.settings) {
                val appSettingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + activity!!.packageName))
                startActivityForResult(appSettingsIntent, requestCode)
            }
            .show()
}

fun Fragment.requestPermission(permission: String, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(arrayOf(permission), requestCode)
    }
}

fun Fragment.requestPermissionWithRationale(@StringRes message: Int, permission: String, requestCode: Int) {
    if (activity == null) return

    if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!,
                    permission)) {
        showPermissionSnackbar(message, requestCode)
    } else requestPermission(permission, requestCode)
}