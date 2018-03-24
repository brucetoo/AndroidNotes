package com.brucetoo.androidnotes

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_dp.*
import kotlinx.coroutines.experimental.NonCancellable.cancel
import org.jetbrains.anko.alert
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    //observable代表每次对参数 赋值后 会回调，且值肯定是会改变
    var observerName: String by Delegates.observable("Tom"){
        property, oldValue, newValue ->
        Log.e("MainActivity","property:$property, oldValue:$oldValue, newValue:$newValue")
//        println("property:$property, oldValue:$oldValue, newValue:$newValue")
    }

    //vetoable代表每次对参数 赋值前 会回调，并且此回调的返回值决定是否参数改变与否（true:改变 false:不可变）
    var vetoableName: String by Delegates.vetoable("Tom vetoable"){
        property, oldValue, newValue ->
        Log.e("MainActivity", "property:$property, oldValue:$oldValue, newValue:$newValue")
        true
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_dp)

        for (index in 0..layout_base.childCount) {
            val child = layout_base.getChildAt(index) as? TextView
            if (child != null) {
                val text = child.text
                child.viewTreeObserver.addOnDrawListener {
                    child.text = "$text:${child.height}PX"
                }
                child.viewTreeObserver.removeOnDrawListener {  }
            }
        }

        observerName = "brucetoo"
        vetoableName = "brucetoo vetoable"

//        showAreYouSureAlert {
//            Log.e("alert", "show are you sure alert...")
//        }

//        MainActivityUI().setContentView(this)
//
//        val btn = Button(this)
////        btn.viewTreeObserver
//        verticalLayout {
//            val email = editText {
//                hint = "email"
//            }
//            val password = editText {
//                hint = "password"
//                transformationMethod = PasswordTransformationMethod.getInstance()
//            }
//            button {
//                textResource = R.string.app_name
//                onClick {
//                    startActivity(intentFor<MainActivity>("id" to 5).singleTop())
//                    startActivity<MainActivity>("id" to 5)
//                }
//            }
//
//            seekBar {
//                onSeekBarChangeListener {
//                    onProgressChanged { seekBar, progress, fromUser ->
//
//                    }
//                }
//            }
//        }
    }
}

fun Activity.showAreYouSureAlert(process: () -> Unit) {
    alert(title = "Are you sure?",
            message = "Are you really sure?")
    {
        positiveButton("Yes") { process() }
        negativeButton("No") { cancel() }
    }.show()
}


