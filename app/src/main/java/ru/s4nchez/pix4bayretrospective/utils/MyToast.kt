package ru.s4nchez.pix4bayretrospective.utils

import android.content.Context
import android.widget.Toast

class MyToast private constructor(applicationContext: Context) {

    companion object {

        @Volatile private var instance: MyToast? = null

        operator fun get(context: Context) =
                instance ?: synchronized(this) {
                    instance ?: MyToast(context.applicationContext)
                }
    }

    private val toast: Toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)

    fun show(vararg messages: Any) {
        val text = StringBuilder()
        for (i in messages.indices) text.append(messages[i].toString()).append("\n")
        toast.setText(text.toString().trim { it <= ' ' })
        toast.show()
    }
}
