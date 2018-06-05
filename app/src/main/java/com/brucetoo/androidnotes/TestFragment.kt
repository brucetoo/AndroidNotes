package com.brucetoo.androidnotes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Bruce Too
 * On 08/05/2018.
 * At 10:06
 */
class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val text = TextView(container?.context)
        text.text = arguments.getInt("position").toString()
        text.textSize = 25f
        text.gravity = Gravity.CENTER
        Log.i("TestFragment","index " + arguments.getInt("position"))
        return text
    }

}