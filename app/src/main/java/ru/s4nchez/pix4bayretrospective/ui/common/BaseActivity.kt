package ru.s4nchez.pix4bayretrospective.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import ru.s4nchez.pix4bayretrospective.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    protected fun setFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        manager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}
