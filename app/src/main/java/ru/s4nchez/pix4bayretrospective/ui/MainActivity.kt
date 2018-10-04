package ru.s4nchez.pix4bayretrospective.ui

import android.os.Bundle
import ru.s4nchez.pix4bayretrospective.ui.common.BaseActivity
import ru.s4nchez.pix4bayretrospective.ui.list.ListView

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setFragment(ListView.newInstance(), false)
        }
    }
}
