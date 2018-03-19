package com.brucetoo.androidnotes

import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Bruce Too
 * On 19/03/2018.
 * At 16:34
 */
class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            orientation = LinearLayout.VERTICAL
            padding = dip(10)
            val text = editText {
                hint = "this is test AnkoComponent"
            }
            button("Click me!") {
                onClick {
                    ctx.toast("hello, ${text.text}")
                }
            }
        }
    }
}