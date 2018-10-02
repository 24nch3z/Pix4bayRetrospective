package ru.s4nchez.pix4bayretrospective.utils

import android.view.View

fun View.visibilityByFlag(flag: Boolean) {
    this.visibility = if (flag) View.VISIBLE else View.GONE
}