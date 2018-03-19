package com.brucetoo.androidnotes

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.coroutines.experimental.NonCancellable.cancel
import org.jetbrains.anko.alert
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showAreYouSureAlert {
            Log.e("alert", "show are you sure alert...")
        }

        MainActivityUI().setContentView(this)
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


