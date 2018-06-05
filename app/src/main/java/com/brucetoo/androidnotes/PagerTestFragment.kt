package com.brucetoo.androidnotes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.backgroundColor

/**
 * Created by Bruce Too
 * On 08/05/2018.
 * At 10:06
 */
class PagerTestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val pager = ViewPager(container?.context!!)
        pager.offscreenPageLimit = 1
        pager.adapter = MyAdapter()
        return pager
    }

    class MyAdapter : PagerAdapter() {

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return 8
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val text = TextView(container.context)
            text.text = position.toString()
            text.textSize = 25f
            text.gravity = Gravity.CENTER
            if (text.parent == null) {
                container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            }
            container.backgroundColor = R.color.colorPrimary
            Log.i("instantiateItem", "index $position")
            return text
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }
    }

}