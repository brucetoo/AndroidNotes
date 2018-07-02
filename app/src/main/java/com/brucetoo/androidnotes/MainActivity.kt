package com.brucetoo.androidnotes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brucetoo.androidnotes.fragment.ConstraintLayoutFragment
import com.brucetoo.androidnotes.fragment.NestedScrollFragment
import com.brucetoo.androidnotes.fragment.OkioFragment
import kotlinx.android.synthetic.main.layout_dp.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.experimental.NonCancellable.cancel
import org.jetbrains.anko.alert
import org.jetbrains.anko.backgroundColor
import java.util.*
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


    val fragments: ArrayList<String> = ArrayList()

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_dp)

        addFragments()

        recycler_view.adapter = RecyclerAdapter(fragments,this)
    }

    private fun addFragments(){
        this.fragments.add(PagerTestFragment::class.java.simpleName)
        this.fragments.add(ConstraintLayoutFragment::class.java.simpleName)
        this.fragments.add(NestedScrollFragment::class.java.simpleName)
        this.fragments.add(OkioFragment::class.java.simpleName)
    }

    public fun navigate(fragment: String){
        var item: Fragment = PagerTestFragment()
        when (fragment) {
            "PagerTestFragment" -> item = PagerTestFragment()
            "ConstraintLayoutFragment" -> item = ConstraintLayoutFragment()
            "NestedScrollFragment" -> item = NestedScrollFragment()
            "OkioFragment" -> item = OkioFragment()
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, item)
                .addToBackStack("")
                .commit()
    }
}

class RecyclerAdapter(val fragments: ArrayList<String>,val context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fragment.text = fragments[position]
        holder.itemView.backgroundColor = randomColor()
        holder.itemView.setOnClickListener {
            if(context is MainActivity){
                context.navigate(fragments[position])
            }
        }
    }

    fun randomColor(): Int{
        var rnd =  Random()
        return Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val fragment = view.tv_fragment
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


