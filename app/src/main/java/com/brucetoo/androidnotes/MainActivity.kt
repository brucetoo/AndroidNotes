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
import com.brucetoo.androidnotes.fragment.*
import com.brucetoo.androidnotes.shapereplace.BackgroundLibrary
import com.brucetoo.androidnotes.tools.RefHandler
import kotlinx.android.synthetic.main.layout_dp.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.experimental.NonCancellable.cancel
import org.jetbrains.anko.alert
import org.jetbrains.anko.backgroundColor
import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

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


    val fragments: ArrayList<KClass<out Fragment>> = ArrayList()

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        BackgroundLibrary.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_dp)

        addFragments()

        checkRefHandler()

        recycler_view.adapter = RecyclerAdapter(fragments,this)
    }

    private fun checkRefHandler() {
        var handler: RefHandler<MainActivity> = RefHandler(this, RefHandler.MessageCallback { referSelf, msg -> Log.i("handleMessage", "out ") })
        handler.sendEmptyMessage(1)
    }

    private fun addFragments(){
        this.fragments.add(PagerTestFragment::class)
        this.fragments.add(ConstraintLayoutFragment::class)
        this.fragments.add(NestedScrollFragment::class)
        this.fragments.add(OkioFragment::class)
        this.fragments.add(JumpingBeansFragment::class)
        this.fragments.add(OkHttpFragment::class)
        this.fragments.add(SpanFragment::class)
        this.fragments.add(ShapeReplaceFragment::class)
        this.fragments.add(NoClipPagerFragment::class)
    }

    public fun navigate(fragment: KClass<out Fragment>){
        var item: Fragment = fragment.createInstance()

//        when (fragment) {
//            "PagerTestFragment" -> item = PagerTestFragment()
//            "ConstraintLayoutFragment" -> item = ConstraintLayoutFragment()
//            "NestedScrollFragment" -> item = NestedScrollFragment()
//            "OkioFragment" -> item = OkioFragment()
//        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, item)
                .addToBackStack("")
                .commit()
    }
}

class RecyclerAdapter(val fragments: ArrayList<KClass<out Fragment>>,val context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fragment.text = fragments[position].java.simpleName
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


