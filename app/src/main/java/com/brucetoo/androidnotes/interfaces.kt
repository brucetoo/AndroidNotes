package com.brucetoo.androidnotes

import android.content.Context
import android.util.AttributeSet

/**
 * Created by Bruce Too
 * On 20/03/2018.
 * At 09:59
 */
class interfaces {

    interface Clickable {
        //1.接口定义方法时  abstract 默认被省略
        fun click()

        //2.接口可以有默认的实现，而java中不可~ 有默认实现的子类可以不继承
        fun showOff() = print("i'm clickable")
    }

    interface Focusable {
        fun showOff() = println("I'm focusable!")
    }

    //类实现方法
    class Button : Clickable, Focusable {

        //3.kotlin中使用override是强制性的(mandatory)
        override fun click() = print("You clicked me~~~~")

        //4.如果多个接口同时有相同的方法需要继承，则需要显式的在子类继承
        override fun showOff() {
            //5.显式继承 Clickable的showOff方法
            super<Clickable>.showOff()
        }
    }

    //open的User有具一个参数的primary constructor
    open class User(val name: String) {
        var address: String = "unspecified"
            set(value: String) {
                println()
                field = value
            }
    }

    //子类继承User，也必须在父类引用有加括号初始化父类
    class TwitterUser(name: String) : User(name) {}

    //secondary constructor,跟java中类似
    open class View {
        constructor(ctx: Context) {}
        constructor(ctx: Context, attr: AttributeSet) {}
    }

    interface Users {
        //接口中定义属性nickname
        val nickname: String
    }

    //1、在primary constructor中直接实现Users中的抽象属性nickname
    class UserImpl1(override val nickname: String) : Users {}

    //2、通过自定义getter方式实现nickname属性
    class UserImpl2 : Users {
        override val nickname: String
            get() = nickname.substringBefore(".")
    }

    class DelegateCollection<T>(innerList: Collection<T> = ArrayList<T>()) : Collection<T> by innerList {

    }

    object Payroll {
        val allEmployees = arrayListOf<Person>()
    }

    data class Person(val name: String) {
        object NameComparator : Comparator<Person> {
            override fun compare(p1: Person, p2: Person): Int = p1.name.compareTo(p2.name)
        }
    }

    val person = Person("name")
    val item = Item("type1",2,1.0f,false)

    fun test(){
        item.copy(type1 = "", type2 = 1, type3 = 10f, type4 = true)
        SingleTon.b()
    }
    val String.lashChar: Char
        get() = get(length - 1)

    data class Item(var type1: String?,
                    var type2: Int,
                    var type3: Float,
                    var type4: Boolean)

    object SingleTon{
        fun b(){
            println("SingleTon")
        }
    }

    //Use
//    SingleTon.b()

}