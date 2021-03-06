package ru.s4nchez.pix4bayretrospective.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment() {

    protected abstract val layout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    protected fun setFragment(fragment: Fragment, addToBackStackFlag: Boolean) {
        (activity as BaseActivity).setFragment(fragment, addToBackStackFlag)
    }
}
