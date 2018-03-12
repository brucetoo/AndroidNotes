package com.brucetoo.androidnotes

/**
 * Created by Bruce Too
 * On 27/02/2018.
 * At 15:58
 */
class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }

    class Inner2 {
        //嵌套类不会持有外部引用
    }

    var outer = Outer()

    //extension functions
    fun String.lastChar(): Char = this.get(this.length - 1)

    fun testString(str: String) {
        val test = "我才能打"
        val lastChar = test.lastChar()
    }

    fun testMemberReference(str: String) {
        var people = listOf(1, 2, 3, 4, 5)

        fun getStringTest(): String = "1"

//    data class Person(val name: String, val age: Int)

        val createPerson = ::Person
        val p = createPerson("name", 1)

        fun Person.isAdult(): Boolean = this.age >= 21
        val predicate = Person::isAdult

        val peoples = listOf(Person("Name1", 1), Person("Name2", 2))
        //已名字平铺显示，采用member reference
        peoples.map(Person::name)
        //谓词替换
        var ageGt1 = { p: Person -> p.age > 1 }
        peoples.count { it.age > 1 }
        peoples.count(ageGt1)//以上两种表达方式结果一致

        //迭代maps
        val personMap = mapOf(0 to "zero",1 to "one")
        personMap.mapValues { it.value.toUpperCase() }

        val list = listOf("a","ab","b")
        list.groupBy { it.first() }
        list.groupBy(String::first)//采用Member reference形式

        val index: Int = 1;
        index.coerceIn(1, 100)


    }

    fun strLenSafa(s: String?) : Int =
            //因为s标记了非null，因此直接使用s.length检查是会报错的
        if(s != null) s.length else 0

    fun String?.isNullOrBlack(): Boolean = this == null || this.isBlank()

    fun checkUserInput(input: String?){
        //采用extension function在调用处规避了null的问题，而不是在调用前规避null
        if(input.isNullOrBlack()){
            //....
        }
    }
}

data class Person(val name: String, val age: Int)

//自定义方法，lambda作为函数入参
private fun twoAndThree(operation: (Int, Int) -> Int) {
    val result = operation(2, 3)
    println("the result is $result")

    //返回一个function type，已Order为入参，int为返回值
    val calculator = getShippingCostCalculator(Delivery.STANDARD)
    println("the calculator cost ${calculator(Order(3))}")
}

enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

//定义一个函数，以Delivery为入参，(Order) -> Int 方法为返回值
fun getShippingCostCalculator(delivery: Delivery): (Order) -> Int {
    if (delivery == Delivery.EXPEDITED) {
        return { order -> order.itemCount * 2 + 6 }
    }
    return { order -> order.itemCount + 6 }
}